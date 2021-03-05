var cellsize = 3;
var backgroundColor = 'rgb(220, 220, 220)'; // light grey
var cellColor = 'rgb(0, 0, 0)'; // Black
var stopIt = false;
var canvas = document.getElementById('canvas');
var ctx = canvas.getContext('2d');


function log(str) {
    console.log(str);
}
function gridArg(g, stringify) {
    var s = [];
    for (i=0; i < g.length; i++) {
        var row = [];
        for (j=0; j < g[i].length; j++) {
            row.push(stringify(g[i][j]));
        }
        s.push(row.join(''));
    }
    return s.join(',');
}

function getRequestBody(methodName, args, stringify) { // stringify is a function that renders a value into a string for transmission
    var sr = new Array();
    sr.push('<?xml version="1.0" encoding="utf-8"?>');
    sr.push('<soapenv:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" ' +
            'xmlns:api="http://sscomputing.com" ' +
            'xmlns:xsd="http://www.w3.org/2001/XMLSchema" ' +
            'xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">');
    sr.push('<soapenv:Body>');
    sr.push('<api:' + methodName + ' soapenv:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">');
    for (a=0; a < args.length; a++) {
        sr.push('<arg' + a + '>');
        if (Array.isArray(args[a])) {
            sr.push(gridArg(args[a], stringify));
        } else {
            console.log('A:' + args[a]);
            sr.push(args[a].toString());
        }
        sr.push('</arg' + a + '>');
    }
    sr.push('</api:' + methodName + '>');
    sr.push('</soapenv:Body>');
    sr.push('</soapenv:Envelope>');
    log(sr);
    return sr.join('');
}

async function sendRequest(req, serviceName) {
    var url = window.location.href.replace(/[^/]+$/, serviceName);
    console.log('URL:' + url);
    var response = await fetch(url, {
        method: 'POST',
        body: req,
        headers: {
            'Content-Type': 'text/xml'
        }
    });
    response = await response.text();
    log('Got Response' + response);
    return await response;
}

function XMLtoGrid(xml, unstringify) { //unstringify gets a value based on a string
    var d = new Date();
    console.log('StartTime:' + d.toLocaleTimeString());
    var result = getXMLReturn(xml);
    var newgrid = [];
    result.split(',').forEach(row => {
        log('RA:' + row + '/');
        newgrid.push([]);
        const datums = row.split(' ');
        for (j=0; j < datums.length; j++) {
            newgrid[newgrid.length-1].push(unstringify(datums[j]));
        }
    });
    d = new Date();
    console.log('endTime:' + d.toLocaleTimeString());
    return newgrid;
}
function getXMLReturn(xml) {
    const parser = new DOMParser();
    const doc = parser.parseFromString(xml, 'text/xml');
    const resultString = doc.evaluate('//return', doc, null, XPathResult.ANY_TYPE, null);
    var result = resultString.iterateNext().textContent;
    return result;
}
function showBooleanGrid(grid) {
    var d = new Date();
    console.log('Show StartTime:' + d.toLocaleTimeString());
    for (i=0; i < grid.length; i++) {
        if (i*cellsize >= canvas.offsetHeight-1) stopIteration();
        for (j=0; j < grid[i].length; j++) {
            if (j*cellsize >= canvas.offsetWidth-1) stopIteration();
            ctx.fillStyle = backgroundColor;
            if (grid[i][j]) ctx.fillStyle = cellColor;
            ctx.fillRect(j*cellsize, i*cellsize, cellsize, cellsize);
        }
    }
    d = new Date();
    console.log('Show endTime:' + d.toLocaleTimeString());
}
function showDoubleGrid(grid) {
    var d = new Date();
    console.log('Show StartTime:' + d.toLocaleTimeString());
    for (i=0; i < grid.length; i++) {
        if (i*cellsize >= canvas.offsetHeight-1) stopIteration();
        for (j=0; j < grid[i].length; j++) {
            if (j*cellsize >= canvas.offsetWidth-1) stopIteration();
            var r = Math.min(128*grid[i][j], 255);
            var b = 100;
            var g = 100;
            ctx.fillStyle = 'rgb(' + r + ',' + b + ',' + g + ')';
            ctx.fillRect(j*cellsize, i*cellsize, cellsize, cellsize);
        }
    }
    d = new Date();
    console.log('Show endTime:' + d.toLocaleTimeString());
}
async function sayHello(service) {
    var sr = getRequestBody('sayHello', '');
    var result = await sendRequest(sr, service);
    var response = getXMLReturn(result.toString());
    log('Response:' + response);
    return response;
}
async function runIterations(initFunc, iterationFunc) {
    var grid = initFunc();
    console.log('Iterations' + document.getElementById("iterations"));
    for (ii=0; ii < document.getElementById("iterations").value && !stopIt; ii++) {
        console.log('Iteration ' + (ii+1));
        grid = await iterationFunc(grid);
    }
    return grid;
}
function stopIteration() {
    console.log('Stopping iterations upon request');
    stopIt = true;
}
function clearGrid() {
    console.log('Clearing ..');
    ctx.clearRect(0,0,500,500);
    stopIt = false;
}

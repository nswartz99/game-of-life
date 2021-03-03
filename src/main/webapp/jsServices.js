var cellsize = 3;
var backgroundColor = 'rgb(220, 220, 220)'; // light grey
var cellColor = 'rgb(0, 0, 0)'; // Black
var stopIt = false;
var canvas = document.getElementById('canvas');
var ctx = canvas.getContext('2d');


function log(str) {
    console.log(str);
}
function getRequestBody(g) {
    var sr = new Array();
    sr.push('<?xml version="1.0" encoding="utf-8"?>');
    sr.push('<soapenv:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" ' +
            'xmlns:api="http://sscomputing.com/game-of-life/GameOfLife" ' +
            'xmlns:xsd="http://www.w3.org/2001/XMLSchema" ' +
            'xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">');
    sr.push('<soapenv:Body>');
    sr.push('<api:iterateCompact soapenv:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">');
    sr.push('<arg0>');
    var s = [];
    for (i=0; i < g.length; i++) {
        var row = [];
        for (j=0; j < g[i].length; j++) {
            row.push(g[i][j] ? '1' : '0');
        }
        s.push(row.join(''));
    }
    sr.push(s.join(','));
    sr.push('</arg0>');
    sr.push('</api:iterateCompact>');
    sr.push('</soapenv:Body>');
    sr.push('</soapenv:Envelope>');
    log(sr);
    return sr.join('');
}

async function sendRequest(req) {
    var url = window.location.href.replace(/[^/]+$/, 'GameOfLifeService');
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

function XMLtoGrid(xml) {
    var d = new Date();
    console.log('StartTime:' + d.toLocaleTimeString());
    const parser = new DOMParser();
    const doc = parser.parseFromString(xml, 'text/xml');
    const resultString = doc.evaluate('//return', doc, null, XPathResult.ANY_TYPE, null);
    var newgrid = [];
    var result = resultString.iterateNext().textContent;
    result.split(',').forEach(row => {
        log('RA:' + row);
        newgrid.push([]);
        for (j=0; j < row.length; j++) {
            if (row[j].match('1')) newgrid[newgrid.length-1].push(true); else newgrid[newgrid.length-1].push(false);
        }
    });
    d = new Date();
    console.log('endTime:' + d.toLocaleTimeString());
    return newgrid;
}

function showGrid(grid) {
    var d = new Date();
    console.log('Show StartTime:' + d.toLocaleTimeString());
    for (i=0; i < grid.length; i++) {
        if (i*cellsize >= canvas.offsetHeight-1) stopLife();
        for (j=0; j < grid[i].length; j++) {
            if (j*cellsize >= canvas.offsetWidth-1) stopLife();
            ctx.fillStyle = backgroundColor;
            if (grid[i][j]) ctx.fillStyle = cellColor;
            ctx.fillRect(j*cellsize, i*cellsize, cellsize, cellsize);
        }
    }
    d = new Date();
    console.log('Show endTime:' + d.toLocaleTimeString());
}
async function iterate(grid) {
    var sr = getRequestBody(grid);
    var result = await sendRequest(sr);
    var newgrid = XMLtoGrid(result.toString());
    log('Result:' + newgrid.join(','));
    showGrid(newgrid);
    return newgrid;
}
async function runLife() {
    var grid = [[false, true, true], [true, true, false], [false, true, false]]; // rpentomino
//                var grid = [[false,false,false,false], [false, true, true, true], [false, false, false, true], [false, false, true, false]]; // Glider
    console.log('Iterations' + document.getElementById("iterations"));
    for (ii=0; ii < document.getElementById("iterations").value && !stopIt; ii++) {
        console.log('Iteration ' + (ii+1));
        grid = await iterate(grid);
    }
    return grid;
}
function stopLife() {
    console.log('Stopping iterations upon request');
    stopIt = true;
}
function clearGrid() {
    console.log('Clearing ..');
    ctx.clearRect(0,0,500,500);
    stopIt = false;
}

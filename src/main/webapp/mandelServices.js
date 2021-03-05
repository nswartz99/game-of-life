
function init() {}

async function iterateMandel() {
    var sr = getRequestBody('iterate', [
        document.getElementById("iterations").value,
        document.getElementById("dimension").value - 1,
        document.getElementById("xStart").value,
        document.getElementById("yStart").value,
        document.getElementById("xEnd").value,
        document.getElementById("yEnd").value]);
    var result = await sendRequest(sr, 'MandelbrotService');
    var newgrid = XMLtoGrid(result.toString(), str => (str.match(/NaN/) ? 1.0E100 : str + ''));
    log('Result:' + newgrid.join(','));
    showDoubleGrid(newgrid);
    return newgrid;
}


function init() {}

function initializeRPentomino() {
    var grid = [[false, true, true], [true, true, false], [false, true, false]]; // rpentomino
    return grid;
}
function initializeGlider() {
    var grid = [[false,false,false,false], [false, true, true, true], [false, false, false, true], [false, false, true, false]]; // Glider
    return grid;
}
async function iterateLife(grid) {
    var sr = getRequestBody('iterateCompact', grid, v => (v ? '1' : '0'));
    var result = await sendRequest(sr, 'GameOfLifeService');
    var newgrid = XMLtoGrid(result.toString(), str => (str.match('1') ? true : false));
    log('Result:' + newgrid.join(','));
    showBooleanGrid(newgrid);
    return newgrid;
}

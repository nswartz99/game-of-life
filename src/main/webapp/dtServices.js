function init() {}

async function iterateDoubleTest() {
    var sr = getRequestBody('iterate', ['']);
    var result = await sendRequest(sr, 'DoubleTestService');
    log('XML:' + result.toString());
    var newgrid = XMLtoGrid(result.toString(), str => (str.match('0') ? true : false));
    log('Result:' + newgrid.join(','));
//    showBooleanGrid(newgrid);
    return newgrid;
}
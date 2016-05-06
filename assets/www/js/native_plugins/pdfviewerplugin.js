var EbookPdfViewer = function() {};
            
EbookPdfViewer.prototype.show = function(content, success, fail) {
    return cordova.exec( function(args) {
        success(args);
    }, function(args) {
        fail(args);
    }, 'EbookPdfViewer', '', [content]);
};

if(!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.pdfviewerplugin) {
    window.plugins.pdfviewerplugin = new EbookPdfViewer();
}
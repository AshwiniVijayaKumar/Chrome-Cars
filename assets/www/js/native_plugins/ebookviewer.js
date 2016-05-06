var EbookViewer = function() {};
            
EbookViewer.prototype.show = function(content, success, fail) {
    return cordova.exec( function(args) {
        success(args);
    }, function(args) {
        fail(args);
    }, 'EbookViewer', '', [content]);
};

if(!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.ebookviewer) {
    window.plugins.ebookviewer = new EbookViewer();
}
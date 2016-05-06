var DownloadUnzip = function() {};
            
DownloadUnzip.prototype.show = function(content, success, fail) {
    return cordova.exec( function(args) {
        success(args);
    }, function(args) {
        fail(args);
    }, 'DownloadUnzip', '', [content]);
};

if(!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.downloadunzip) {
    window.plugins.downloadunzip = new DownloadUnzip();
}

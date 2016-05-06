var BellaReaderSite = function() {};
            
BellaReaderSite.prototype.show = function(content, success, fail) {
    return cordova.exec( function(args) {
        success(args);
    }, function(args) {
        fail(args);
    }, 'BellaReaderSite', '', [content]);
};

if(!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.bellareadersite) {
    window.plugins.bellareadersite = new BellaReaderSite();
}

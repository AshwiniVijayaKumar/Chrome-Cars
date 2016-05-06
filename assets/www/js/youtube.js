var YouTube = function() {};
            
YouTube.prototype.show = function(content, success, fail) {
    return cordova.exec( function(args) {
        success(args);
    }, function(args) {
        fail(args);
    }, 'YouTube', '', [content]);
};

if(!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.youtube) {
    window.plugins.youtube = new YouTube();
}

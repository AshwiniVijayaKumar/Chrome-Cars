var PlayerViewer = function() {};

PlayerViewer.prototype.show = function(content, success, fail) {
    return cordova.exec( function(args) {
        success(args);
    }, function(args) {
        fail(args);
    }, 'PlayerViewer', '', [content]);
};

if(!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.playerplugin) {
    window.plugins.playerplugin = new PlayerViewer();
}
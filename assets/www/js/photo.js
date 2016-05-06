var Photo = function() {};
            
Photo.prototype.show = function(content, success, fail) {
    return cordova.exec( function(args) {
        success(args);
    }, function(args) {
        fail(args);
    }, 'Photo', '', [content]);
};

if(!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.photo) {
    window.plugins.photo = new Photo();
}

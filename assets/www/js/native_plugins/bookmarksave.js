var BookmarkSave = function() {};
            
BookmarkSave.prototype.show = function(content, success, fail) {
    return cordova.exec( function(args) {
        success(args);
    }, function(args) {
        fail(args);
    }, 'BookmarkSave', '', [content]);
};

if(!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.bookmarksave) {
    window.plugins.bookmarksave = new BookmarkSave();
}

var BookMarkDelete = function() {};
            
BookMarkDelete.prototype.show = function(content, success, fail) {
    return cordova.exec( function(args) {
        success(args);
    }, function(args) {
        fail(args);
    }, 'BookMarkDelete', '', [content]);
};

if(!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.bookmarkdelete) {
    window.plugins.bookmarkdelete = new BookMarkDelete();
}

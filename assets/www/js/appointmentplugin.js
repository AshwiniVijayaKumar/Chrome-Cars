var AppointmentPlugin = function() {};     
AppointmentPlugin.prototype.appointmentServiceCall = function(content, success, fail) {
    return cordova.exec( function(args) {
        success(args);
    }, function(args) {
        fail(args);
    }, 'AppointmentPlugin', '', [content]);
};

if(!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.appointmentplugin) {
    window.plugins.appointmentplugin = new AppointmentPlugin();
}
/**
 * SQuiz2
 * skurrilewelt new media
 * 
 * (c)2013
 * 
 * @author Olaf Sweekhorst
 * @package wp-content.plugins.SQuiz2.js
 * @version 1.0
 * 
 */
/**
 * 
 * pathToImages points to the plugin directory and must be assigned in the
 * view php
 * 
 * @version 4.0.0
 * 
 * draggable questions possible
 * 
 */
;(function(jQuery) {
	var pluginName = 'scheck';

	function Plugin(element, options) {
		var el = element;
		var jQueryel = jQuery(element);

		options = jQuery.extend({}, jQuery.fn[pluginName].defaults, options);
		var wrong = false;
		var checked = false;
		var image;
		var imageUnchecked = "";
		var imageChecked = "";
		
		function init() {
			var pathToImages = (pathToImages == undefined) ? "." : pathToImages;
			if(options.type != "order" ) {
				imageUnchecked = (options.type=="check") ? 	pathToImages + "/images/quiz/22-unchecked.gif" : pathToImages + "/images/quiz/22-unchecked-radio.gif";
				imageChecked = (options.type=="check") ? 	pathToImages + "/images/quiz/22-checked.gif" : pathToImages + "/images/quiz/22-checked-radio.gif";
				image = jQuery("<img src='" + imageUnchecked + "' />").appendTo(element); 
				image.css({
					cursor:'pointer',
					position:'absolute',
					verticalAlign:'middle',
					width: 22,
					height: 22,
					top: (jQuery(element).height()- image.height()) /2
				}).click(clickMe); 
			
				if(options.mode == "review" && wrong == true) {
					image.src = pathToImages + "/images/quiz/22-cross.gif";
				} else if (options.mode == "review" && wrong == false) {
					image.src = pathToImages + "/images/quiz/22-check.gif";
				}
			}
			var label = jQuery("<label>", {css:{width:'auto', paddingRight:10, cursor:'pointer'}}).appendTo(element).text(options.label); 
			if(options.type != "order" ) label.click(clickMe);
			hook('onInit');
		}

		
		function clickMe() {
                      // alert(isAnswerEditable);
                      if(!isAnswerEditable)
                      {
                      if(options.mode == "quiz")
                      {
                      checked = !checked;
                      toggleImage();
                      }
                      hook('onValueChanged');
                      }
                            }
		
		function toggleImage() {
			if(checked) {
                image.attr("src", imageChecked);
            } else {
                image.attr("src", imageUnchecked);
            }
		};
		
		
		//  ==============  public accessivle functions  ===================
		/**
		 * @param value Boolean
		 * @param trigger Boolean
		 */
		function setValue(value,trigger) {
			checked = value;
			//if(trigger) hook('onValueChanged');
			toggleImage();
		};
		
		/**
		 * @returns String checked | ""
		 */
		function getValue() {
			if (checked){
				return 'checked';
			} else {
				return "";
			}
		};
		
		function option (key, val) {
			if (val) {
				options[key] = val;
			} else {
				return options[key];
			}
		};

		function destroy() {
			jQueryel.each(function() {
				var el = this;
				var jQueryel = jQuery(this);

				// Add code to restore the element to its original state...

				hook('onDestroy');
				jQueryel.removeData('plugin_' + pluginName);
			});
		};

		function hook(hookName) {
			if (options[hookName] !== undefined) {
				options[hookName].call(el);
			}
		};

		init();

		return {
			option: option,
			destroy: destroy,
			getValue: getValue,
			setValue: setValue
		};
	}

	jQuery.fn[pluginName] = function(options) {
		if (typeof arguments[0] === 'string') {
			var methodName = arguments[0];
			var args = Array.prototype.slice.call(arguments, 1);
			var returnVal;
			this.each(function() {
				if (jQuery.data(this, 'plugin_' + pluginName) && typeof jQuery.data(this, 'plugin_' + pluginName)[methodName] === 'function') {
					returnVal = jQuery.data(this, 'plugin_' + pluginName)[methodName].apply(this, args);
				} else {
					throw new Error('Method ' +  methodName + ' does not exist on jQuery.' + pluginName);
				}
			});
			if (returnVal !== undefined){
				return returnVal;
			} else {
				return this;
			}
		} else if (typeof options === "object" || !options) {
			return this.each(function() {
				if (!jQuery.data(this, 'plugin_' + pluginName)) {
					jQuery.data(this, 'plugin_' + pluginName, new Plugin(this, options));
				}
			});
		}
	};

	jQuery.fn[pluginName].defaults = {
			onInit: function() {},
			onDestroy: function() {},
			onValueChanged: function () {},
			type: "check",
			mode: "quiz",
			label: "Label"
	};

})(jQuery);
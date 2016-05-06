function CallbackBillingPlugin() {
	//alert('krishna');
}

CallbackBillingPlugin.prototype.test = function(success, fail) {
//alert('krishna1');
	cordova.exec(success, fail, "CallbackBillingPlugin", "test", []);
};

CallbackBillingPlugin.prototype.requestPurchase = function(success, fail, productId) {
//alert('krishna2');
	cordova.exec(success, fail, "CallbackBillingPlugin", "requestPurchase", [productId]);
};

CallbackBillingPlugin.prototype.getPurchasedItems = function(success, fail) {
//alert('krishna3');
	console.log('CallbackBillingPlugin.prototype.getPurchasedItem');
	cordova.exec(success, fail, "CallbackBillingPlugin", "getPurchasedItems", []);
};

CallbackBillingPlugin.prototype.restoreDatabase = function(success, fail) {
//alert('krishna4');
	console.log('CallbackBillingPlugin.prototype.restoreDatabase');
	cordova.exec(success, fail, "CallbackBillingPlugin", "restoreDatabase", []);
};


/* function(transactionIdentifier, productId, transactionReceipt) */
CallbackBillingPlugin.prototype.onPurchaseStateChange = null;

/* function(originalTransactionIdentifier, productId, originalTransactionReceipt) */
CallbackBillingPlugin.prototype.onRequestPurchaseResponse = null;

/* function(errorCode, errorText) */
CallbackBillingPlugin.prototype.onRestoreTransactionsResponse = null;


CallbackBillingPlugin.prototype.updatedTransactionCallback = function(state, errorCode, errorText, transactionIdentifier, productId, transactionReceipt) {
	//alert(state);
	switch(state) {
		case "PaymentTransactionStatePurchased":
			if(this.onPurchaseStateChange) {
				this.onPurchaseStateChange(transactionIdentifier, productId, transactionReceipt);
			} else {
				this.eventQueue.push(arguments);
				this.watchQueue();
			}
			return; 

		case "PaymentTransactionStateFailed":
			if(this.onRequestPurchaseResponse) {
				this.onRequestPurchaseResponse(errorCode, errorText);
			} else {
				this.eventQueue.push(arguments);
				this.watchQueue();
			}
			return;

		case "PaymentTransactionStateRestored":
			if(this.onRestoreTransactionsResponse) {
				this.onRestoreTransactionsResponse(transactionIdentifier, productId, transactionReceipt);
			} else {
				this.eventQueue.push(arguments);
				this.watchQueue();
			}
			return;
	}
};

/*
 * This queue stuff is here because we may be sent events before listeners have been registered. This is because if we have 
 * incomplete transactions when we quit, the app will try to run these when we resume. If we don't register to receive these
 * right away then they may be missed. As soon as a callback has been registered then it will be sent any events waiting
 * in the queue.
 */

CallbackBillingPlugin.prototype.runQueue = function() {
//alert('krishna6');
	if(!this.eventQueue.length || (!this.onPurchaseStateChange && !this.onRequestPurchaseResponse && !this.onRestoreTransactionsResponse)) {
		return;
	}
	var args;
	/* We can't work directly on the queue, because we're pushing new elements onto it */
	var queue = this.eventQueue.slice();
	this.eventQueue = [];
	while (args = queue.shift()) {
		this.updatedTransactionCallback.apply(this, args);
	}
	if (!this.eventQueue.length) {	
		this.unWatchQueue();
	}
}

CallbackBillingPlugin.prototype.watchQueue = function() {
//alert('krishna7');
	if(this.timer) {
		return;
	}
	this.timer = setInterval("window.plugins.CallbackBillingPlugin.runQueue()", 10000);
}

CallbackBillingPlugin.prototype.unWatchQueue = function() {
//alert('krishna8');
	if(this.timer) {
		clearInterval(this.timer);
		this.timer = null;
	}
}

CallbackBillingPlugin.prototype.eventQueue = [];
CallbackBillingPlugin.prototype.timer = null;

window.CallbackBillingPlugin = new CallbackBillingPlugin();
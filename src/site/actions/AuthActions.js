var AppDispatcher = require('../dispatcher/AppDispatcher');
var AuthConstants = require('../constants/AuthConstants');
var AjaxFunctions = require('../common/AjaxFunctions');

var AuthActions = {

  updateUser: function(updatedUser, done) {
    var apiUrl = "/updateUser";
    AjaxFunctions.post(apiUrl, updatedUser, function(err) {
      done(err);
    }.bind(this));
  },
  updateLogin: function(loginStatus) {
    AppDispatcher.dispatch({
      actionType: AuthConstants.UPDATE_LOGIN,
      data: loginStatus
    });
  },

  signup: function(signupData, done) {

    var apiUrl = "/signup";
    AjaxFunctions.post(apiUrl, signupData, done);
  },


  login: function(loginData, done) {

    var apiUrl = "/login";
    AjaxFunctions.post(apiUrl, loginData, function(err, data) {
      if (!err) {
        this.updateLogin(true);
      }
      done(err, data);
    }.bind(this));

  },


  logout: function() {
    var apiUrl = "/logout";

    AjaxFunctions.get(apiUrl, function(err) {
      if (err) {
        console.log("error logging out", err);
      }
      else {

        console.log("user logged out")
        this.updateLogin(false);
      }
    }.bind(this));
  }
};

module.exports = AuthActions;

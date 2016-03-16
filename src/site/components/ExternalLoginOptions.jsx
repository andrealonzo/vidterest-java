/** @jsx React.DOM */
'use strict'
var React = require("react");
module.exports = React.createClass({
		  render:function(){
		    return(
		        <div className="modal-content">
      <div className="modal-header">
        <button type="button" className="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 className="modal-title text-center" id="myModalLabel">Login</h4>
      </div>
      <div className="modal-body text-center">
    
        <div>
        <a className="btn btn-primary btn-lg btn-block login-button" href="/auth/facebook" role="button">
            <div className="row ">
              <div className="col-xs-1 text-left">
						<i className="fa fa-facebook fa-md modal-icons"></i> 
						</div>
						
  						<div className="col-xs-10 text-left">
						 LOGIN WITH FACEBOOK
						</div>
						
						</div>
						</a>
		</div>
		<div>
		Recommended.  We will never post anything without your permission.
		</div>
        <div>
        <a className="btn btn-danger btn-lg btn-block login-button" href="/auth/twitter" role="button">
           <div className="row ">
              <div className="col-xs-1 text-left">
  						<i className="fa fa-twitter fa-md modal-icons"></i> 
  						</div>
  						<div className="col-xs-10 text-left">
  						 LOGIN WITH TWITTER
  						</div>
						</div>
						</a>
		</div>
		
        <div>
        <a className="btn btn-warning btn-lg btn-block login-button" href="/auth/github" role="button">
         <div className="row ">
              <div className="col-xs-1 text-left">
							<i className="fa fa-github fa-md modal-icons"></i> 
							</div>
							<div className="col-xs-10 text-left">
						 LOGIN WITH GITHUB
						</div>
						</div>
						</a>
		</div>
		<hr></hr>
		<div>
		or use your email address
		<div className="row">
		  <div className="col-xs-6">
    		<a className="btn btn-default btn-block" role="button" onClick={this.props.onLoginClick}>
    		Login
    		</a>
    		</div>
		  <div className="col-xs-6">
    		<a className="btn btn-default btn-block" role="button" onClick={this.props.onSignupClick}>
    		Signup
    		</a>
    		</div>
    	</div>
		</div>
		
      </div>
      <div className="modal-footer">
      </div>
    </div>

)
		  }
		});
		
import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Box from '@material-ui/core/Box';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Radio from '@material-ui/core/Radio';
import FoodBoxLogo from './FoodBoxLogo.png';
import { withRouter } from "react-router-dom";
import './Login.css';
const axios = require('axios').default;

class Login extends React.Component {


constructor(props) {
	super(props);
	this.state = {
		userName: "",
		password: "",
		userType: "customer",
		loginFailed: ""
	}
	this.handleChange = this.handleChange.bind(this);
	this.loginUser = this.loginUser.bind(this);
}


handleChange(content) {
	this.setState(content);
}

loginUser(event) {
	event.preventDefault();
	axios.post("/api/" + this.state.userType + "/login", { userName: this.state.userName, password: this.state.password }).then(
		response => {
			this.props.changeUser(response.data, "login");
		}
	).catch(err => {
		console.log(err.response.data);
		this.setState({ userName: "", password: "", loginFailed: err.response.data });
	});
}

createAccount=()=>{
	
	this.props.history.push("/register");
}

render() {
	return (
		<Grid container>
			<Grid item xs={4}>
			</Grid>

			<Grid item xs={4}>
				<div className="container">
					<img className="logo" alt="foodBoxLogo" src={FoodBoxLogo} />
					<Typography variant="body1" color="error">
						{this.state.loginFailed}
					</Typography>
					<form onSubmit={this.loginUser}>
						<TextField
							variant="outlined"
							margin="normal"
							required
							fullWidth
							label="User Name"
							type="text"
							value={this.state.userName}
							autoFocus
							onChange={event => this.handleChange({ userName: event.target.value })}
						/>
						<TextField
							variant="outlined"
							margin="normal"
							required
							fullWidth
							label="Password"
							type="password"
							value={this.state.password}
							onChange={event => this.handleChange({ password: event.target.value })}
						/>
						<FormControl component="fieldset">
							<RadioGroup row aria-label="UserType" name="userType" value={this.state.userType} onChange={event => this.handleChange({ userType: event.target.value })} className="radioGroup">
								<FormLabel component="legend">Login as a:&nbsp;&nbsp;</FormLabel> 
								<FormControlLabel value="customer" control={<Radio />} label="Customer" />
								<FormControlLabel value="admin" control={<Radio />} label="Admin" />
							</RadioGroup>
						</FormControl>
						<br />
						<br />
						<Button
							type="submit"
							fullWidth
							variant="contained"
							color="primary"
						>Login
              			</Button>
						<br />
						<br />
						<Button
							type="button"
							fullWidth
							variant="contained"
							color="primary"
							onClick={this.createAccount}
						>Create Account
	              		</Button>
						<Box mt={5}>
							<Typography variant="body2" color="textSecondary" align="center">
								{'Copyright © FoodBox_LauraFlach '}
								{new Date().getFullYear()}
								{'.'}
							</Typography>
						</Box>
					</form>
				</div>
			</Grid>
		</Grid>
	);
}
}

{/*export default Login;*/}
export default withRouter(Login);
import React from 'react';
import {
	AppBar, Toolbar, Typography, Grid
} from '@material-ui/core';
import { MuiThemeProvider, createMuiTheme } from '@material-ui/core/styles';
import './TopBar.css';
import UserMenu from "./UserMenu"
import AdminMenu from "./AdminMenu"
const axios = require('axios').default;

const theme = createMuiTheme({
	palette: {
		secondary: {
			main: '#34cbcb'
		},
		
	},
});

class TopBar extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			view: this.props.view
		}
		this.logoutUser = this.logoutUser.bind(this);
	}

	componentDidUpdate() {
		if (this.state.view !== this.props.view) {
			this.setState({ view: this.props.view });
		}
	}

	logoutUser() {
		let type = this.props.currentUser.type;
		this.props.changeUser(undefined, "logout");
		axios.post("/api/" + type + "/logout", {}).then(
			() => {
				console.log("Successfully log out");
			}
		).catch(err => {
			console.log(err);
		});
	}

	render() {
		return this.props.currentUser ? (
			<MuiThemeProvider theme={theme}>
				<AppBar className="topbar" position="absolute" color="secondary">
					<Toolbar>
						{this.props.currentUser.type ==="customer" ?
							(<Grid
								container
								direction="row"
								justify="space-between"
								alignItems="center"
							>
								<Grid item>
									<Typography variant="h5" color="inherit" classes="white">
										Hello {this.props.currentUser.userName}
									</Typography>
								</Grid>
								<Grid item>
									<Typography variant="h5">{this.state.view}</Typography>
								</Grid>
								<Grid item>
									<UserMenu currentUser={this.props.currentUser} logoutUser={this.logoutUser} />
								</Grid>
							</Grid>) : (
									<Grid
									container
									direction="row"
									justify="space-between"
									alignItems="center"
									>
										<Grid item>
											<Typography variant="h5" color="inherit" classes="white">
												Hello {this.props.currentUser.userName}
											</Typography>
										</Grid>
										<Grid item>
											<Typography variant="h5">{this.state.view}</Typography>
										</Grid>
										<Grid item>
											<AdminMenu currentUser={this.props.currentUser} logoutUser={this.logoutUser} />
										</Grid>
									</Grid>)}
					</Toolbar>
				</AppBar>
			</MuiThemeProvider>
		): (<MuiThemeProvider theme={theme}>
				<AppBar className="topbar" position="absolute" color="secondary">
					<Toolbar>
					<Typography variant="h4"><i><b>Welcome! Please Login</b></i></Typography>
					</Toolbar>
				</AppBar>
			</MuiThemeProvider>
			);
	}
}

export default TopBar;
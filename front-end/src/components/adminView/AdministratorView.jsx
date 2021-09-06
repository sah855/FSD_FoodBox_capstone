import React from 'react';
import { HashRouter as Router, Redirect, Route, Switch} from 'react-router-dom';
import {
  Grid
} from '@material-ui/core';
import RestaurantOrder from "./mainpage/RestaurantOrder";
import AdministratorHome from "./mainpage/AdministratorHome";
import RestaurantHistory from "./mainpage/RestaurantHistory";
import RestaurantInfo from "./mainpage/RestaurantInfo";
import RestaurantMenu from "./mainpage/RestaurantMenu";

class RestaurantView extends React.Component {
  constructor(props) {
    super(props);
    this.props.changeView("Administrator")
  }
  render() {
    return this.props.currentUser ? (
      <Router>
        <Grid container justify="space-evenly">
          {/* <Grid item sm={3}>
            <RestaurantBar />
          </Grid>*/}
          <Grid item sm={9}>
            <div className="grid-main">
              <Switch>
                <Route path="/admin/home" render={props => <AdministratorHome {...props} currentUser={this.props.currentUser} />} />
                <Route path="/admin/restaurant/information/:restaurantId" render={props => <RestaurantInfo {...props} currentUser={this.props.currentUser} />} />
                <Route path="/admin/restaurant/menu/:restaurantId" render={props => <RestaurantMenu {...props} currentUser={this.props.currentUser} />} />
                <Route path="/admin/restaurant/order/:restaurantId" render={props => <RestaurantOrder {...props} currentUser={this.props.currentUser} />} />
                <Route path="/admin/restaurant/history/:restaurantId" render={props => <RestaurantHistory {...props} currentUser={this.props.currentUser} />} />
                <Redirect path="/admin" to="/admin/home" />
              </Switch>
            </div>
          </Grid>
        </Grid>
      </Router>
    ) : <div />;
  }
}

export default RestaurantView;
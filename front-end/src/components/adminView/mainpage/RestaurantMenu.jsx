import React from 'react';
import Button from '@material-ui/core/Button';
import {
  Grid
} from '@material-ui/core';
import DishCard from "../../card/DishCard";
import EmptyDish from '../../card/EmptyDish';
import { withRouter } from "react-router-dom";

const axios = require('axios').default;

class RestaurantMenu extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
	  restaurantId: this.props.match.params.restaurantId,
      restaurant: undefined,
      menu : undefined
    }
	this.getRestaurant = this.getRestaurant.bind(this);
    this.getAllDishes = this.getAllDishes.bind(this);
  }

  componentDidMount() {
	this.getRestaurant();
    this.getAllDishes();
  }

  getRestaurant() {
    axios.get("/api/restaurant/" + this.state.restaurantId).then(
      response => {
        this.setState({restaurant: response.data});
      }
    ).catch(err => console.log(err));
  }

  getAllDishes() {
    axios.get("/api/restaurant/menu/" + this.state.restaurantId).then(
      response => {
        this.setState({menu: response.data});
      }
    ).catch(err => console.log(err));
  }

	updateInfo=()=>{	
		this.props.history.push("/admin/restaurant/information/"+this.props.currentUser.id);
	}

  render() {
    return this.props.currentUser && this.state.restaurant ? (
      <Grid container spacing={3} justify="space-evenly">
		
        {this.state.menu ? 
          (this.state.menu.map((dish, index) => (
            <Grid item xs={3} key={index}>
              <DishCard dish={dish} getAllDishes={this.getAllDishes} currentUser={this.props.currentUser} />
            </Grid>
          ))) : null
        }
        <Grid item xs={3}>
          	<EmptyDish getAllDishes={this.getAllDishes} currentUser={this.props.currentUser} />

			<Button
				type="button"
				fullWidth
				variant="contained"
				color="primary"
				onClick={this.updateInfo}
			>Update Restaurant Information
			</Button>	
        </Grid>

      </Grid>	

    ) : <div>The restaurant is not available</div>
  }
}

export default RestaurantMenu;
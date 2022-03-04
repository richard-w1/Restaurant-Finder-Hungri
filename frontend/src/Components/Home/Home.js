import React from 'react';
import {Link} from 'react-router-dom';
import {Component} from 'react';
import SearchForm from '../SearchForm/SearchForm';

// const Home = (props) => {
//     return(
//         <div>
//             You must be authorized to see this page.
//         </div>
//     )
// }

class Home extends Component {
    constructor(props){
        super(props);
    }

    render() {
        return(
            <div class = "input" >
                    <h1>Home</h1>
                    <Link to="/SearchForm">
                        <button renderAs="button">
                            <span>Search Restaurants</span>
                        </button>
                    </Link>
                    <Link to="/CreateGroup">
                        <button renderAs="button">
                            <span>Create an Invite</span>
                        </button>
                    </Link>
                    <Link to="/ViewGroup">
                        <button renderAs="button">
                            <span>See Your Existing Invitations</span>
                        </button>
                    </Link>

            </div>
        );
    }

}
export default Home;
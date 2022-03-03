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
            <>
                <SearchForm />
            </>
        );
    }

}
export default Home;
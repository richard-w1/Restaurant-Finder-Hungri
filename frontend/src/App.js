import React from 'react';
import Main from './Components/Main/Main';
import Header from './Components/UI/Header';
import Home from './Components/Home/Home';
import { Provider } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import { ConfigureStore } from './Redux/configureStore';



const store = ConfigureStore();

function App() {

  return ( 
  
    <Provider store={store}>
      <BrowserRouter>
          <Main/>
      </BrowserRouter>
    </Provider>
  );
}

export default App;
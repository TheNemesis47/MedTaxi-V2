import React from 'react';
import { useNavigate } from 'react-router-dom';

function Home() {
    const navigate = useNavigate();

    return (
        <div>
            <h1>Home Page</h1>
            <div className={"columns-2"}>
                <button className={"w-64"} onClick={() => navigate('/login')}>Login</button>
                <button className={"w-64"} onClick={() => navigate('/register')}>Register</button>
            </div>
            <button>
                <button className={"w-64"} onClick={() => navigate("/ViewUser")}>ADMIN</button>
            </button>
        </div>
    );
}

export default Home;

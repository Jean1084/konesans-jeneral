import 'react-toastify/dist/ReactToastify.css';
import Navbar from "../components/Navbar/Navbar";
import Footer from "../components/Footer/Footer";
import { Outlet } from "react-router-dom";
import { ToastContainer } from "react-toastify";

const Layout = () => {

    return (
        <>
            <Navbar />
            <ToastContainer />
            <main>
                <Outlet />
            </main>
            <Footer />
        </>
    )
};

export default Layout;
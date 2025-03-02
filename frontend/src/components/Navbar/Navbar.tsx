import styles from './Navbar.module.css';
import { Link } from "react-router-dom";
import Login from '../login/Login';
import Profile from '../Profile/Profile';
import { useUser } from '../../context/UserContext';
import { User } from '../../models/User';

const Navbar = () => {
    const { user } = useUser();

    return (
        <nav className={styles.navbar}>
            <Link className={styles.logo} to="/">
                <img src="/logo.svg" alt="Logo" />
            </Link>
            <div className={styles.right}>
                { User.isNotLoggedIn(user) ? <Login style='icon' /> : <Profile /> }
            </div>
        </nav>
    );
}

export default Navbar;
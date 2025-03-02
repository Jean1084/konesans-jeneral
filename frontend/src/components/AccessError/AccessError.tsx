import { useUser } from '../../context/UserContext';
import { User } from '../../models/User';
import Login from '../login/Login';
import styles from './AccessError.module.css';
import { FiUserX } from 'react-icons/fi';

type AccessErrorProps = {
    message: string;
}

const AccessError = ({message}: AccessErrorProps) => {

    const { user } = useUser();

    return (
        <div className={styles.container}>
            <FiUserX className={styles.icon} />
            <p>{message}</p>
            {User.isNotLoggedIn(user) && <Login style='button' />}
        </div>
    )
}

export default AccessError;
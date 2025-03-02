import styles from './Login.module.css';
import { FcGoogle } from "react-icons/fc";
import { RxGithubLogo } from "react-icons/rx";
import { CgProfile } from "react-icons/cg";
import { LOGIN_PAGE_TITLE, LOGIN_WITH_GITHUB, LOGIN_WITH_GOOGLE } from '../../../constants/Constants';
import { config } from "../../../config/Config";

const Login = () => {

    const login = (provider: string) => {
        window.location.href = `${config.BACKEND_BASE_URL}/oauth2/authorization/${provider}`;
    }

    return (
        <div className={styles.container}>
            <CgProfile className={styles.icon} />
            <h1>{LOGIN_PAGE_TITLE}</h1>
            <div className={styles.signinOptions}>
                <button onClick={() => login('google')} className={styles.signinOption}>
                    <FcGoogle className={styles.signinOptionIcon} /> 
                    {LOGIN_WITH_GOOGLE}
                </button>
                <button onClick={() => login('github')} className={styles.signinOption}>
                    <RxGithubLogo className={styles.signinOptionIcon} />
                    {LOGIN_WITH_GITHUB}
                </button>
            </div>
        </div>
    );
};

export default Login;
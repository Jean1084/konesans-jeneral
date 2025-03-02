import { useUser } from "../../context/UserContext";
import { LOGGED_IN_RESERVED_PAGE } from "../../constants/Constants";
import { Outlet } from "react-router";
import AccessError from '../../components/AccessError/AccessError';
import { User } from "../../models/User";

const LoggedInPage = () => {

  const { user } = useUser();

  return User.isLoggedIn(user) ? <Outlet />  : <AccessError message={LOGGED_IN_RESERVED_PAGE} />;

}

export default LoggedInPage;



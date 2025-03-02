import { useUser } from "../../context/UserContext";
import { ADMIN_RESERVED_PAGE } from "../../constants/Constants";
import { Outlet } from "react-router";
import AccessError from '../../components/AccessError/AccessError';
import { User } from "../../models/User";

const AdminPage = () => {

  const { user } = useUser();

  return User.isAdmin(user) ? <Outlet />  : <AccessError message={ADMIN_RESERVED_PAGE} />;

}

export default AdminPage;



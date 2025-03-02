import UserService from '../../services/UserService'
import { useEffect } from 'react'
import { User } from '../../models/User'
import { useUser } from '../../context/UserContext'
import { Navigate, useLocation } from 'react-router-dom'
import { URL_BEFORE_LOGIN } from '../../constants/Constants'

const OAuth2Redirect = () => {
  const location = useLocation();
  const { user, setUser } = useUser();
  const redirectUrl = localStorage.getItem(URL_BEFORE_LOGIN) || '/';

  useEffect(() => {
    const urlParams = new URLSearchParams(location.search);
    const accessToken = urlParams.get('access_token');
    const refreshToken = urlParams.get('refresh_token');
    if (!accessToken || !refreshToken) return;
    const loggedInUser = User.parse(accessToken, refreshToken);
    setUser(loggedInUser);
    UserService.saveUser(loggedInUser);
  }, [location, user, setUser]);

  return (
    <Navigate to={redirectUrl} />
  )
}

export default OAuth2Redirect;

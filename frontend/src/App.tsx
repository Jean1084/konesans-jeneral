import './App.css'
import { UserContext } from './context/UserContext'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Layout from './pages/Layout'
import AdminPage from './pages/Admin/AdminPage'
import ManageQuizzes from './pages/Admin/ManageQuizzes/ManageQuizzes'
import ImportQuiz from './pages/Admin/ImportQuiz/ImportQuiz'
import AddQuiz from './pages/Admin/AddQuiz/AddQuiz'
import UpdateQuiz from './pages/Admin/UpdateQuiz/UpdateQuiz'
import ManageUsers from './pages/Admin/ManageUsers/ManageUsers'
import LoggedInPage from './pages/LoggedIn/LoggedInPage'
import PlayQuiz from './pages/LoggedIn/PlayQuiz/PlayQuiz'
import History from './pages/LoggedIn/History/History'
import Home from './pages/Public/Home/Home'
import Login from './pages/Public/Login/Login'
import OAuth2Redirect from './components/OAuth2Redirect/OAuth2Redirect'
import NoPage from './pages/Public/NoPage/NoPage'
import { useEffect, useState } from 'react'
import UserService from './services/UserService'

const App = () => {

    const [user, setUser] = useState(UserService.getUser());

    useEffect(() => {
        setInterval(() => UserService.refreshUser(setUser), 60000)
    }, []);

    return (
        <UserContext.Provider value={{user, setUser}}>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Layout />}>
                        <Route element={<AdminPage />}>
                            <Route path="/admin/quiz" element={<ManageQuizzes />} />
                            <Route path="/admin/quiz/import" element={<ImportQuiz />} />
                            <Route path="/admin/quiz/ajout" element={<AddQuiz />} />
                            <Route path="/admin/quiz/edit/:id" element={<UpdateQuiz />} />
                            <Route path="/admin/users" element={<ManageUsers />} />
                        </Route>
                        <Route element={<LoggedInPage />}>
                            <Route path="/quiz/:id" element={<PlayQuiz />} />
                            <Route path="/history" element={<History />} />
                        </Route>
                        <Route index element={<Home />} />
                        <Route path='/login' element={<Login />} />
                        <Route path='/oauth2/redirect' element={<OAuth2Redirect />} />
                        <Route path="*" element={<NoPage />} />
                    </Route>
                </Routes>
            </BrowserRouter>
        </UserContext.Provider>
    );
}

export default App

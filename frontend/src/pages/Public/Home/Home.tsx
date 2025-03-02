import styles from './Home.module.css';
import { useEffect, useState } from "react";
import Banner from "./Banner/Banner";
import HttpClient from '../../../services/HttpClient';
import { Quiz } from '../../../models/Quiz';
import PageTitle from '../../../components/PageTitle/PageTitle';
import { HOME_PAGE_TITLE } from '../../../constants/Constants';
import QuizCard from '../../../components/QuizCard/QuizCard';
import Loader from '../../../components/Loader/Loader';

const Home = () => {
    
    const [loading, setLoading] = useState<boolean>(true);
    const [quizzes, setQuizzes] = useState<Quiz[]>([]);

    useEffect(() => {
        HttpClient.get<Quiz[]>("/quizzes").then(res => {
            setQuizzes(res.data);
            setLoading(false);
        })
    }, []);

    return (
        <>
            <Banner />
            <PageTitle value={HOME_PAGE_TITLE} />
            <Loader visible={loading} />
            <div className={styles.quizzes}>
                {quizzes.map(quiz => <QuizCard key={quiz.id} quiz={quiz} />)}
            </div>
        </>
    );
};

export default Home;
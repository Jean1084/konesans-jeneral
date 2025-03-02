import styles from './Link.module.css';
import { Tooltip } from 'react-tooltip';
import { Link } from 'react-router-dom';
import { VIEW } from '../../constants/Constants';
import { MdOutlineRemoveRedEye } from "react-icons/md";

type ViewLinkProps = {
    to: string;
}

const ViewLink = ({to}: ViewLinkProps) => {

    return (
        <>
            <Tooltip id="view" />
            <Link 
                data-tooltip-id="view"  
                data-tooltip-content={VIEW}
                to={to}>
                <MdOutlineRemoveRedEye className={[styles.action, styles.view].join(' ')} />
            </Link>
        </>
    );
}

export default ViewLink;
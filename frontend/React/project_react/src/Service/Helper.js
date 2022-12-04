import image_user from '../images/user.png';

export default function ProfileImage(user) {
    return (user && user.imageId) ? "/api/images/download/" + user.imageId : image_user;
}
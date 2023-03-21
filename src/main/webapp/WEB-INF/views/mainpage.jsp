<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Meetex</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href='<c:url value='/css/bootstrap.min.css'/>'>
    <link rel="stylesheet" href='<c:url value='/css/style.css'/>'>
    <link rel="stylesheet" href='<c:url value='/css/font-awesome.min.css'/>'>
    <link rel="stylesheet" href='<c:url value='/css/owl.theme.default.css'/>'>
    <link rel="stylesheet" href='<c:url value='/css/owl.carousel.min.css'/>'>
    <meta charset="utf-8"/>
</head>
<!--header-->


<body>
<nav>
    <div class="first">
        <h4>
            Meetex
        </h4>
        <div class="search">
            <i class="fa fa-search"></i>
            <input type="search" name="" id="" placeholder="Search..."/>
        </div>
    </div>

    <div class="second">
        <i class="fa fa-globe"></i>
        <i class="fa fa-comment-o"></i>
        <div class="profile">
            <img src='<c:url value='/css/img/avatar/hero.png'/>' alt=""/>
        </div>
        <div class="dots">
            <span class="dot"></span>
            <span class="dot"></span>
        </div>
    </div>
</nav>

<!--main content -sidenav - posts - stories - chats -->
<div class="main">
    <div class="row">
        <!--sidenav-->
        <div class="col-12 col-sm-12 col-md-12 col-lg-2 col-xl-2 sidenav">
            <div class="header">
                <h5>
                    menu
                </h5>
                <i class="fa fa-cog"></i>
            </div>
            <div class="list">
                <ul>
                    <li class="active">
                        <a href="">
                            <i class="fa fa-home"></i>
                            home
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <i class="fa fa-users"></i>
                            freinds
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <i class="fa fa-calendar"></i>
                            events
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <i class="fa fa-image"></i>
                            photos
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <i class="fa fa-play"></i>
                            videos
                        </a>
                    </li>
                </ul>
            </div>

            <!--favs-->
            <div class="favs">
                <div class="header">
                    <h4>
                        favourites
                    </h4>

                    <i class="fa fa-heart"></i>
                </div>

                <div class="list">
                    <ul>
                        <li>
                            <div class="category">
                                <div class="image">
                                    <img src='<c:url value='/css/img/favs/hiking.svg'/>' alt=""/>
                                </div>
                                <p>hiking</p>
                            </div>
                            <span>22</span>
                        </li>
                        <li>
                            <div class="category">
                                <div class="image">
                                    <img src='<c:url value='/css/img/favs/sunbed.svg'/>' alt=""/>
                                </div>
                                <p>travelling</p>
                            </div>
                            <span>12+</span>
                        </li>
                        <li>
                            <div class="category">
                                <div class="image">
                                    <img src='<c:url value='/css/img/favs/fried.svg'/>' alt=""/>
                                </div>
                                <p>cooking</p>
                            </div>
                            <span>32</span>
                        </li>
                        <li>
                            <div class="category">
                                <div class="image">
                                    <img src='<c:url value='/css/img/favs/electric-guitar.svg'/>' alt=""/>
                                </div>
                                <p>rock and roll</p>
                            </div>
                            <span>19+</span>
                        </li>
                        <li>
                            <div class="category">
                                <div class="image">
                                    <img src='<c:url value='/css/img/favs/player.svg'/>' alt=""/>
                                </div>
                                <p>hockey</p>
                            </div>
                            <span>45</span>
                        </li>
                        <li>
                            <div class="category">
                                <div class="image">
                                    <img src='<c:url value='/css/img/favs/basketball.svg'/>' alt=""/>
                                </div>
                                <p>basket</p>
                            </div>
                            <span>14</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!--posts and stroies-->
        <div class="col-12 col-sm-12 col-md-12 col-lg-8 col-xl-8 bigArea">
            <div class="row">
                <div class="col-12 col-sm-12 col-md-12 col-lg-8 col-xl-8 parent">
                    <!--create post - choose image- live video -->
                    <div class="update">
                        <div class="upper">
                            <div class="selection">
                                <p>
                                    <i class="fa fa-pencil"></i>
                                    make a post
                                </p>
                            </div>
                            <div class="selection">
                                <p>
                                    <i class="fa fa-image"></i>
                                    photo/video album
                                </p>
                            </div>
                            <div class="selection">
                                <p class="none">
                                    <i class="fa fa-camera"></i>
                                    live video
                                </p>
                            </div>
                        </div>
                        <!-- write post -->
                        <div class="write">
                            <div class="profile">
                                <img src='<c:url value='/css/img/avatar/hero.png'/>' alt=""/>

                            </div>
                            <textarea name="" id="" placeholder="What's on your mind ? How do you feel ?"></textarea>
                        </div>
                        <!--tags and activity-->
                        <div class="activity">
                            <div class="selection">
                                <p>
                                    <i class="fa fa-user"></i>
                                    tag people
                                </p>
                            </div>
                            <div class="selection">
                                <p>
                                    <i class="fa fa-map-marker"></i>
                                    check in
                                </p>
                            </div>
                            <div class="selection">
                                <p class="none">
                                    <i class="fa fa-smile-o"></i>
                                    feeling/Activity
                                </p>
                            </div>
                        </div>
                    </div>

                    <!-- STORIES AND SUGGESTED -->
                    <div class="timeline">
                        <div class="post-info">
                            <div class="perosn">
                                <div class="profile">
                                    <img src='<c:url value='/css/img/avatar/7.jpg'/>' alt=""/>
                                </div>
                                <div class="desc">
                                    <h6>Emily stone</h6>
                                    <p>is feeling excited 🤩 with <span>patrick jones</span>
                                        <br> january/6/2020
                                    </p>
                                </div>
                            </div>
                            <!-- settings dots -->
                            <div class="dots">
                                <span></span>
                                <span></span>
                            </div>
                        </div>

                        <!-- post images -->
                        <div class="post-content">
                            <p>Travellimg to the future 🌟
                                , it's alraedy 2020 🙋‍♂🌈🌴</p>
                            <div class="imgs">
                                <div class="left">
                                    <img src='<c:url value='/css/img/post/1.jpg'/>' alt=""/>

                                </div>

                                <div class="right">
                                    <div class="sm up-img">
                                        <img src='<c:url value='/css/img/post/2.jpg'/>' alt=""/>
                                    </div>
                                    <div class="sm down-img">
                                        <img src='<c:url value='/css/img/post/3.jpg'/>' alt=""/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- actions -->
                        <div class="actions">
                            <div class="selection">
                                <p>
                                    <i class="fa fa-thumbs-up"></i>
                                    like
                                </p>
                            </div>

                            <div class="selection">
                                <p>
                                    <i class="fa fa-comment-o"></i>
                                    comment
                                </p>
                            </div>

                            <div class="selection">
                                <p class="none">
                                    <i class="fa fa-share"></i>
                                    share
                                </p>
                            </div>
                        </div>
                        <!--reactions-->
                        <div class="reations">
                            <i class="fa fa-heart"></i>
                            <i class="fa fa-thumbs-up"></i>
                            <p>Omar Yassir, Jon Snow and 35 others</p>
                        </div>
                        <!--add comment-->
                        <div class="comment">
                            <div class="add">
                                <div class="profile">
                                    <img src='<c:url value='/css/img/avatar/hero.png'/>' alt=""/>
                                </div>
                                <input type="text" placeholder="Write a comment...">
                            </div>
                            <div class="other">
                                <i class="fa fa-camera"></i>
                                <i class="fa fa-smile-o"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- stories -->
                <div class="col-12 col-sm-12 col-md-12 col-lg-8 col-xl-4 stories">
                    <div class="parent">
                        <div class="story-slider">
                            <div class="header">
                                <h4>stories</h4>
                                <i class="fa fa-newspaper-o"></i>
                            </div>
                            <div class="slider-parent">
                                <div class="owl-carousel owl-theme slider">
                                    <div class="item">
                                        <div class="layer">
                                            <div class="person">
                                                <div class="profile">
                                                    <img src='<c:url value='/css/img/avatar/1.jpg'/>' alt=""/>
                                                </div>
                                            </div>
                                            <h6>Omar yassir</h6>
                                        </div>
                                        <div class="story-image">
                                            <img src='<c:url value='/css/img/other/st-1.jpeg'/>' alt=""/>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="layer">
                                            <div class="person">
                                                <div class="profile">
                                                    <img src='<c:url value='/css/img/avatar/2.jpg'/>' alt=""/>

                                                </div>
                                            </div>
                                            <h6>Robin Adams</h6>
                                        </div>
                                        <div class="story-image">
                                            <img src='<c:url value='/css/img/other/st-2.jpeg'/>' alt=""/>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="layer">
                                            <div class="person">
                                                <div class="profile">
                                                    <img src='<c:url value='/css/img/avatar/3.jpg'/>' alt=""/>
                                                </div>
                                            </div>
                                            <h6>Gary simon</h6>
                                        </div>
                                        <div class="story-image">
                                            <img src='<c:url value='/css/img/other/st-3.jpeg'/>' alt=""/>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="layer">
                                            <div class="person">
                                                <div class="profile">
                                                    <img src='<c:url value='/css/img/avatar/4.jpg'/>' alt=""/>
                                                </div>
                                            </div>
                                            <h6>lily Green</h6>
                                        </div>
                                        <div class="story-image">
                                            <img src='<c:url value='/css/img/other/st-4.jpg'/>' alt=""/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="events">
                            <p>
                                <i class="fa fa-calendar"></i>
                                5 events invites
                            </p>
                            <p class="sec">
                                <i class="fa fa-birthday-cake"></i>
                                its Omar yassir's Birthday
                            </p>
                        </div>

                        <div class="pages">
                            <div class="header">
                                <h4>suggested</h4>
                                <i class="fa fa-thumbs-up"></i>
                            </div>
                            <div class="owner">
                                <div class="profile">
                                    <img src='<c:url value='/css/img/avatar/1.jpg'/>' alt=""/>
                                </div>
                                <p>
                                    phoenix Team<br>
                                    <span>designing</span>
                                </p>
                            </div>
                            <div class="page-image">
                                <img src='<c:url value='/css/img/other/page-1.jpg'/>' alt=""/>
                            </div>

                            <div class="like text-center">
                                <p>
                                    <i class="fa fa-heart-o"></i>
                                    like page
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- chats and contacts -->
        <div class="col-12 col-sm-12 col-md-12 col-lg-2 col-xl-2">
            <div class="parent">
                <div class="chatting">
                    <div class="upper">
                        <p class="title">your pages</p>
                        <ul>
                            <li>
                                <div class="basic">
                                    <div class="profile">
                                        <img src='<c:url value='/css/img/other/page-1.jpg'/>' alt=""/>
                                    </div>
                                    <div class="info">
                                        <h6>
                                            script house
                                        </h6>
                                    </div>
                                </div>
                            </li>

                            <li>
                                <div class="basic">
                                    <div class="profile">
                                        <img src='<c:url value='/css/img/other/page-2.jpg'/>' alt=""/>
                                    </div>
                                    <div class="info">
                                        <h6>
                                            phoenix Team
                                        </h6>
                                    </div>
                                </div>
                            </li>

                            <li>
                                <div class="basic">
                                    <div class="profile">
                                        <img src='<c:url value='/css/img/other/page-3.jpeg'/>' alt=""/>
                                    </div>
                                    <div class="info">
                                        <h6>
                                            Flutter developers
                                        </h6>
                                    </div>
                                </div>
                            </li>

                        </ul>

                    </div>

                    <!--online-->
                    <div class="online">
                        <p class="title">contacts (30)</p>
                        <ul>
                            <li>
                                <div class="basic">
                                    <div class="profile">
                                        <img src='<c:url value='/css/img/avatar/1.jpg'/>' alt=""/>
                                    </div>
                                    <div class="info">
                                        <h6>
                                            omar
                                        </h6>
                                    </div>
                                </div>
                                <!--green dot-->
                                <div class="state">
                                </div>
                            </li>
                            <li>
                                <div class="basic">
                                    <div class="profile">
                                        <img src='<c:url value='/css/img/avatar/3.jpg'/>' alt=""/>
                                    </div>
                                    <div class="info">
                                        <h6>
                                            Gary
                                        </h6>
                                    </div>
                                </div>
                                <!--green dot-->
                                <div class="time">
                                    <span> 5 min</span>
                                </div>
                            </li>
                            <li>
                                <div class="basic">
                                    <div class="profile">
                                        <img src='<c:url value='/css/img/avatar/2.jpg'/>' alt=""/>
                                    </div>
                                    <div class="info">
                                        <h6>
                                            robin
                                        </h6>
                                    </div>
                                </div>
                                <!--green dot-->
                                <div class="state">
                                </div>
                            </li>
                            <li>
                                <div class="basic">
                                    <div class="profile">
                                        <img src='<c:url value='/css/img/avatar/4.jpg'/>' alt=""/>
                                    </div>
                                    <div class="info">
                                        <h6>
                                            lily
                                        </h6>
                                    </div>
                                </div>
                                <!--green dot-->

                                <div class="time">
                                    <span> 25 min</span>
                                </div>
                            </li>
                            <li>
                                <div class="basic">
                                    <div class="profile">
                                        <img src='<c:url value='/css/img/avatar/5.jpg'/>' alt=""/>
                                    </div>
                                    <div class="info">
                                        <h6>
                                            emily
                                        </h6>
                                    </div>
                                </div>
                                <!--green dot-->
                                <div class="state">
                                </div>
                            </li>
                            <li>
                                <div class="basic">
                                    <div class="profile">
                                        <img src='<c:url value='/css/img/avatar/6.jpg'/>' alt=""/>
                                    </div>
                                    <div class="info">
                                        <h6>
                                            josh
                                        </h6>
                                    </div>
                                </div>
                                <!--green dot-->
                                <div class="state">
                                </div>
                            </li>
                            <li>
                                <div class="basic">
                                    <div class="profile">
                                        <img src='<c:url value='/css/img/avatar/1.jpg'/>' alt=""/>
                                    </div>
                                    <div class="info">
                                        <h6>
                                            simon
                                        </h6>
                                    </div>
                                </div>
                                <!--green dot-->
                                <div class="state">
                                </div>
                            </li>
                            <li>
                                <div class="basic">
                                    <div class="profile">
                                        <img src='<c:url value='/css/img/avatar/2.jpg'/>' alt=""/>
                                    </div>
                                    <div class="info">
                                        <h6>
                                            sarah
                                        </h6>
                                    </div>
                                </div>
                                <!--green dot-->
                                <div class="state">
                                </div>
                            </li>

                        </ul>

                    </div>
                    <div class="upper">
                        <p class="title">your community</p>
                        <ul>
                            <li>
                                <div class="basic">
                                    <div class="profile">
                                        <img src='<c:url value='/css/img/other/page-1.jpg'/>' alt=""/>
                                    </div>
                                    <div class="info">
                                        <h6>
                                            script house
                                        </h6>
                                    </div>
                                </div>
                            </li>

                            <li>
                                <div class="basic">
                                    <div class="profile">
                                        <img src='<c:url value='/css/img/other/page-2.jpg'/>' alt=""/>
                                    </div>
                                    <div class="info">
                                        <h6>
                                            Flutter Course
                                        </h6>
                                    </div>
                                </div>
                            </li>

                            <li>
                                <div class="basic">
                                    <div class="profile">
                                        <img src='<c:url value='/css/img/other/page-3.jpeg'/>' alt=""/>
                                    </div>
                                    <div class="info">
                                        <h6>
                                            UI/UX Expo
                                        </h6>
                                    </div>
                                </div>
                            </li>

                        </ul>

                    </div>


                </div>
            </div>
        </div>
    </div>
</div>


<!--footer-->
<!--plugin-->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script>
    //story slider
    $(".slider").owlCarousel({
        loop: true,
        margin: 5,
        nav: false,
        dots: false,
        autoplay: true,
        autoplayTimeout: 3000,
        smartSpeed: 1000,
        autoplayHoverPause: true,
        responsive: {
            0: {
                items: 1
            },
            600: {
                items: 1
            },
            1000: {
                items: 2.2
            }
        }
    });
</script>
</body>
</html>
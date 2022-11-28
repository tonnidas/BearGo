import React from 'react';

import image_man from '../images/man_avatar1.jpg';
import image_woman from '../images/women_avatar1.jpg';


export default function MessageChatBox(props) {

  return (
    <>
      <div className="chatapp-conversation">
        <div className="chatapp-header">
          <div className="back">
            <svg className="icon">
              <use href="#icon-back"></use>
            </svg>
          </div>
          <div className="avatar">
            <img src={image_woman} alt="avatar" />
            <div className="user-status status-active">&nbsp;</div>
          </div>
          <div className="user-details">
            <h4>Nusa Penida</h4>
            <a className="view-details" href="javaScript:void(0);">View details <svg className="icon">
              <use href="#icon-right"></use>
            </svg></a>
          </div>
          <div className="option-tooltip">
            <div className="option-tooltip-btn">
              <svg className="icon">
                <use href="#icon-more"></use>
              </svg>
            </div>
            <div className="option-tooltip-inner">
              <div className="option-tooltip-close">
                <i className="icon-close"></i>
              </div>
              <ul>
                <li>Copy Message</li>
                <li>Select Messages</li>
                <li>Remove Messages</li>
                <li>Cancel</li>
              </ul>
            </div>

          </div>
        </div>
        <div className="chatapp-conversation-inner">
          <div className="message message-received">
            <div className="avatar">
              <img src={image_woman} alt="avatar" />
              <div className="user-status status-active">&nbsp;</div>
            </div>
            <ul>
              <li> it can have on financial inclusion especially for low
                <div className="message-action"></div>
              </li>
              <li> financial inclusion
                <div className="message-action"></div>
              </li>
            </ul>
          </div>
          <div className="message message-sent">
            <ul>
              <li>financial inclusion
                <div className="message-action"></div>
              </li>
              <li>Leap over obstacles and speed down
                <div className="message-action"></div>
              </li>
              <li>it can have on financial inclusion especially for low
                <div className="message-action"></div>
              </li>
            </ul>
          </div>
          <div className="message-time"><span>Wednesday, Jun 23, 2021</span></div>
          <div className="message message-received">
            <div className="avatar">
              <img src={image_woman} alt="avatar" />
              <div className="user-status status-active">&nbsp;</div>
            </div>
            <ul>
              <li>Jump too early, and you are sure to wipe out. Jump too late and, well, you get the point.
                <div className="message-action"></div>
              </li>
              <li>are sure to wipe out. Jump too late and
                <div className="message-action"></div>
              </li>
              <li className="attachment-file">
                <img src="images/file.svg" alt="" /> server_pull.php<a
                  href="images/file.svg"><svg className="icon">
                    <use href="#icon-download"></use>
                  </svg></a></li>
            </ul>
          </div>
          <div className="message message-sent">
            <ul>
              <li>are sure to wipe out. Jump too late and
                <div className="message-action"></div>
              </li>
              <div className="message-seen"><img src={image_woman} alt="" />
                <img
                  src={image_woman} alt="" /></div>
              <li>Jump too early, and you are sure to wipe out. Jump too late
                <div className="message-action"></div>
              </li>
            </ul>
          </div>

          <div className="message message-sent">
            <ul>
              <li>are sure to wipe out. Jump too late and
                <div className="message-action"></div>
              </li>
            </ul>
          </div>

          <div className="message message-sent">
            <ul>
              <li className="attachment-file"> <img src="images/file.svg" alt="" /> server_pull.php<a
                href="images/file.svg"><svg className="icon">
                  <use href="#icon-download"></use>
                </svg></a></li>
            </ul>
          </div>
          <div className="message message-received">
            <div className="avatar">
              <img src={image_woman} alt="avatar" />
              <div className="user-status status-active">&nbsp;</div>
            </div>
            <ul>
              <li><a href="#">http://botservice.dotlines.com.sg</a></li>
            </ul>
          </div>
          <div className="message message-sent">
            <ul>
              <li><img src={image_man} alt="" /></li>
            </ul>
          </div>

        </div>

        <div className="chatapp-footer">
          <form action="" method="GET">
            <textarea name="" rows="1" className="form-control pick-me" placeholder="Write a message"></textarea>

            <div className="send-button">
              <button className="btn btn-send"><svg class="icon">
                <use href="#icon-send"></use>
              </svg></button>
            </div>
          </form>
        </div>
      </div>
    </>
  );
}
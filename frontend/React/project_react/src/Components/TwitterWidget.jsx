import React, { useEffect, useState } from 'react';

export default function TwitterWidget(props) {

  return (
    <div className='col-md-4'>
      <div className='twitter-feed'>
        <blockquote
          className='twitter-tweet'
          style={{ marginBottom: '10px' }}
        >
          <p lang='en' dir='ltr'>
            I think it&#39;s entirely appropriate that the courier
            delivery pin to receive my parcel from
            <a href='https://twitter.com/CounterLove?ref_src=twsrc%5Etfw'>
              @CounterLove
            </a>
            was 911 because these limited edition Hertzoggie cookies
            are criminally delicious!
            <a href='https://t.co/1z52eA0Oor'>
              pic.twitter.com/1z52eA0Oor
            </a>
          </p>
          &mdash; Nicole Engelbrecht (@the_pod_one)
          <a href='https://twitter.com/the_pod_one/status/1572841559812411392?ref_src=twsrc%5Etfw'>
            September 22, 2022
          </a>
        </blockquote>
        <blockquote
          className='twitter-tweet'
          style={{ marginBottom: '10px' }}
        >
          <p lang='en' dir='ltr'>
            I think it&#39;s entirely appropriate that the courier
            delivery pin to receive my parcel from
            <a href='https://twitter.com/CounterLove?ref_src=twsrc%5Etfw'>
              @CounterLove
            </a>
            was 911 because these limited edition Hertzoggie cookies
            are criminally delicious!
            <a href='https://t.co/1z52eA0Oor'>
              pic.twitter.com/1z52eA0Oor
            </a>
          </p>
          &mdash; Nicole Engelbrecht (@the_pod_one)
          <a href='https://twitter.com/the_pod_one/status/1572841559812411392?ref_src=twsrc%5Etfw'>
            September 22, 2022
          </a>
        </blockquote>
        <script
          async
          src='https://platform.twitter.com/widgets.js'
          charSet='utf-8'
        ></script>
      </div>
    </div>
  );
}

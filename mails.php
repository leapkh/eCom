<?php

sleep(2);

$mail = new stdClass();
$mail->id = 1;
$mail->sender = 'Sok Sao';
$mail->senderProfile = 'https://rupp-ite.s3.ap-southeast-1.amazonaws.com/acer.jpg';
$mail->subject = 'Hello';
$mail->body = 'How are you?';

$mail2 = new stdClass();
$mail2->id = 2;
$mail2->sender = 'Abc';
$mail2->senderProfile = 'https://rupp-ite.s3.ap-southeast-1.amazonaws.com/dell.jpg';
$mail2->subject = 'Hi';
$mail2->body = 'How do you do?';

$mail3 = new stdClass();
$mail3->id = 3;
$mail3->sender = 'Xyz';
$mail3->senderProfile = 'https://rupp-ite.s3.ap-southeast-1.amazonaws.com/vaio.jpg';
$mail3->subject = 'hihi';
$mail3->body = 'Bye bye';

$mails = array($mail, $mail2, $mail3);

$json = json_encode($mails);

echo $json;
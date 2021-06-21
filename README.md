
<div style="direction: rtl; text-align: right">

## פרויקט סוף סמסטר - שיטות זיהוי תקיפות סייבר

<p dir="rtl">
הפוריקט שלנו זה בעצם תוכנית בשפת ג'אווה אשר תממש מודול הרשמה של משתמשים באופן הבא : 
</p>
<p dir="rtl">
א) 	הרשמה של יוזרים חדשים אשר ישמרו במסד נתונים 
mysql בטבלת Users .

<p dir="rtl">
ב) 	הסיסמאות ישמרו באמצעות מנגנון 
Salted hash כפי שנלמד בשיעור.

<p dir="rtl">
ג) 	המודול יוודא כי המשתמש בוחר סיסמא מורכבת בלבד , היינו סיסמא אשר מכילה ספרות , אותיות גדולות, אותיות קטנות ותווים מיוחדים . 
<p dir="rtl">
ד) 	המודול יוודא כי הסיסמא תהיה לפחות באורך 10 תווים ( יש  לבדוק נתון זה באמצעות קובץ קונפיגורציה) .
<p dir="rtl">
ה) 	במידה והתנאים לא יתקיימו, אזי תשלח הודעת שגיאה למשתמש .

</p>


# ביצוע הפרויקט

## - הכנת בסיס הנתונים :

<p dir="rtl">

הורד את קובץ התמונה המשוכפל של בסיס הנתונים שמצורף לתיקיה הראשית של הריפוסיטורי , הקובץ נקרא : usersdb.sql
טען (import) את הקובץ ב-Phpmyadmin .
</p>

![import database](https://github.com/slampc/cryptographic-final-project/blob/main/images/import_database.png)

## ואז נקבל את בסיס הנתונים שלנו  בצורה הבאה:

![import database](https://github.com/slampc/cryptographic-final-project/blob/main/images/database.png)

כעת נוריד את קובת התוכנה שנקרא:
main.java
מהתיקיה הראשית של הריפוסיטורי.
נפעיל את התוכנה בכל IDE , מומלץ 
Eclipse , כי זה העורך שהשתמשנו בו לכתיבת התוכנית.

## עץ הפרויקטים :

![import database](https://github.com/slampc/cryptographic-final-project/blob/main/images/eclipse_project_tree.png)

## גוף התוכנית - שורות הקוד:

![import database](https://github.com/slampc/cryptographic-final-project/blob/main/images/eclipse_program_body.png)

## הפונקציה שיוצרת את קובץ הקונפיגרציה

![import database](https://github.com/slampc/cryptographic-final-project/blob/main/images/eclipse_create_config_file.png)

## קובץ הקונפיגרציה

בסעיף ד) במטלה של הפרויקט דרוש שהנתון שלפיו גודל הסיסמה יהיה באורך לפחות 10 תווים אמור להיות מאוחסן בקובץ קונפגרציה , אז כתבנו פונקציה שיוצרת קובץ כזה ומאחסנת בתוכו את הנתון הדרוש.
דוגמה של קובץ כזה מאוחסנת בתיקיה הראשית של הריפוסיטורי.

![import database](https://github.com/slampc/cryptographic-final-project/blob/main/images/config_file.png)


## פלט התוכנית - התפריט הראשי:

![import database](https://github.com/slampc/cryptographic-final-project/blob/main/images/eclipse_menu.png)

## - אופציה 1 בתפריט - יצירת טבלה חדשה בבסיס הנתונים :

![import database](https://github.com/slampc/cryptographic-final-project/blob/main/images/eclipse_create_table.png)

## - אופציה 2 בתפריט - הוספת משתמש חדש לבסיס הנתונים  :

## :נסיונות לא מוצלחים בבחירת סיסמה לא חוקית

## סיסמה קצרה

![import database](https://github.com/slampc/cryptographic-final-project/blob/main/images/eclipse_invalid_password.png)

## סיסמה חסרת תווים מכל הסוגים:

![import database](https://github.com/slampc/cryptographic-final-project/blob/main/images/eclipse_invalid_password2.png)


## :סיסמה מוצלחת

![import database](https://github.com/slampc/cryptographic-final-project/blob/main/images/eclipse_valid_password.png)

## :בסיס נתונים וטבלה מעודכנים בנתונים החדשים שהכנסנו בתוכנית

![import database](https://github.com/slampc/cryptographic-final-project/blob/main/images/user_added.png)



</div>
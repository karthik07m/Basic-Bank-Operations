/** 1. Write a query to display the member id, member name, city and membership status who are all having life
time membership. Hint: Life time membership status is “Permanent”.**/
select member_id , member_name, city, membership_status from  lms_members 
where membership_status = "permanent";

/** 2. Write a query to display the member id, member name who have not returned the books. Hint: Book return
status is book_issue_status ='Y' or 'N'. **/
select m.member_name , m.member_id , b.BOOK_ISSUE_STATUS from 
lms_members m inner join  lms_book_issue b where m.member_id = b.member_id and b.BOOK_ISSUE_STATUS ="N";


/**3.Write a query to display the member id, member name who have taken the book with book code 'BL000002'.**/
select  b.member_id, m.member_name from 
lms_members m , lms_book_issue b where  b.book_code ="BL000002";

/**4. Write a query to display the book code, book title and author of the books whose author name begins with 'P'. **/
select book_code, book_title, author from lms_book_details where author
LIKE 'P%';

/**5.Write a query to display the total number of Java books available in library with alias name ‘NO_OF_BOOKS’. **/ 
select count(category) NO_OF_BOOKS from lms_book_details where 
category ="Java";

/**6.Write a query to list the category and number of books in each category with alias name ‘NO_OF_BOOKS’.**/
select category, count(category)as NO_OF_BOOKS from 
lms_book_details  group by category;

/** 7. Write a query to display the number of books published by "Prentice Hall” with the alias name
“NO_OF_BOOKS”. **/
select count(author) NO_OF_BOOKS from lms_book_details where 
PUBLICATION = "Prentice Hall";

/** 8. Write a query to display the book code, book title of the books which are issued on the date "1st April 2012".
**/
select b.book_code, b.book_title from lms_book_details b join  lms_book_issue i
where DATE_ISSUE = "2012-04-01" and b.book_code = i.book_code;

/** 9. Write a query to display the member id, member name, date of registration and expiry date of the members
whose membership expiry date is before APR 2013. **/
select member_id, member_name, date_register from lms_members where 
DATE_EXPIRE < "2013-04-01";

/**10. write a query to display the member id, member name, date of registration, membership status of the
members who registered before "March 2012" and membership status is "Temporary" **/
select member_id, member_name, date_register, membership_status from lms_members where 
date_register < "2012-03-01" and membership_status = "Temporary";

/** 11. Write a query to display the member id, member name who’s City is CHENNAI or DELHI. Hint: Display the
member name in title case with alias name 'Name'. **/
select member_id, member_name Name  from lms_members where 
city ="Delhi" or "Chennai";

/**12. Write a query to concatenate book title, author and display in the following format.
Book_Title_is_written_by_Author
Example: Let Us C_is_written_by_Yashavant Kanetkar
Hint: display unique books. Use “BOOK_WRITTEN_BY” as alias name.**/
select concat(book_title, " _Written_by_ ",author) BOOK_WRITTEN_BY from lms_book_details;

/**13. Write a query to display the average price of books which is belonging to ‘JAVA’ category with alias name “AVERAGEPRICE”. **/
select avg(price) AVERAGEPRICE from lms_book_details where category = "java";

/**14. Write a query to display the supplier id, supplier name and email of the suppliers who are all having gmail account. **/
select supplier_id , supplier_name , email from lms_suppliers_details where 
email Like "%@gmail.com";

/** 15. Write a query to display the supplier id, supplier name and contact details. Contact details can be either phone number or email or address with alias name “CONTACTDETAILS”. If phone number is null then display email, even if email also null then display the address of the supplier. Hint: Use Coalesce function.
**/
select supplier_id , supplier_name, COALESCE (contact, email) Contact 
from lms_suppliers_details;

/** 16. Write a query to display the supplier id, supplier name and contact. If phone number is null then display ‘No’ else display ‘Yes’ with alias name “PHONENUMAVAILABLE”. Hint: Use NVL2.
**/
select supplier_id , supplier_name , 
case 
	when contact is not null then "Yes"
    else "No"
    end
as PHONENUMAVAILABLE from lms_suppliers_details;

/** Average Questions **/

/**1. Write a query to display the member id, member name of the members, book code and book title of the books taken by them. **/
Select bi.member_id,m.member_name,bi.book_code,bd.book_title
from lms_members m 
inner join lms_book_issue bi on bi.member_id = m.member_id
inner join lms_book_details bd on bd.book_code=bi.book_code;

/**2. Write a query to display the total number of books available in the library with alias name “NO_OF_BOOKS_AVAILABLE” (Which is not issued). Hint: The issued books details are available in the LMS_BOOK_ISSUE table.**/
select count(book_code) from lms_book_issue where book_issue_status='N';

/**3. Write a query to display the member id, member name, fine range and fine amount of the members whose fine amount is less than 100.**/
select bi.member_id,m.member_name,bi.fine_range, fd.fine_amount 
from lms_fine_details fd inner join lms_book_issue bi on fd.fine_range=bi.fine_range 
inner join lms_members m on bi.member_id=m.member_id
where fine_amount<100;

/**4. Write a query to display the book code, book title and availability status of the ‘JAVA’ books whose edition is "6”. Show the availability status with alias name “AVAILABILITYSTATUS”. Hint: Book availability status can be fetched from “BOOK_ISSUE_STATUS” column of LMS_BOOK_ISSUE table.**/
select bd.book_code,bd.book_title,bi.book_issue_status AVAILABILITYSTATUS ,bd.book_edition
from LMS_BOOK_ISSUE bi inner join lms_book_details bd on bd.book_code =bi.book_code 
where category="JAVA" AND BOOK_EDITION='6';

/**5. Write a query to display the book code, book title and rack number of the books which are placed in rack 'A1' and sort by book title in ascending order.**/
select book_code,book_title,rack_num from LMS_BOOK_DETAILS where rack_num="A1" order by book_title asc;

/**6. Write a query to display the member id, member name, due date and date returned of the members who has returned the books after the due date. Hint: Date_return is due date and Date_returned is actual book return date.**/
select m.member_id,m.member_name,bi.date_return,bi.date_returned 
from  LMS_BOOK_ISSUE bi
inner join lms_members m on bi.member_id=m.member_id
where date_returned>date_return;

/**7. Write a query to display the member id, member name and date of registration who have not taken any book.**/

select bi.member_id,m.member_name,m.date_register ;
from lms_members m inner join lms_book_issue bi on bi.member_id not =m.member_id


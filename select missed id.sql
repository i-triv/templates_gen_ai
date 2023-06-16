WITH nums AS (
  SELECT LEVEL AS id
  FROM DUAL
  CONNECT BY LEVEL <= (SELECT MAX(id) FROM your_table)
)
SELECT nums.id
FROM nums
LEFT JOIN your_table ON nums.id = your_table.id
WHERE your_table.id IS NULL;
In this query, the WITH clause creates a CTE named nums that generates a sequence of consecutive numbers from 1 up to the maximum ID value in your_table. The LEVEL keyword is used to generate the sequence, and the CONNECT BY clause specifies the number of levels to include in the sequence. The DUAL table is used as a dummy table to satisfy the syntax requirements of the CONNECT BY clause.

The main query then performs a left join between the generated sequence and your_table using the id column as the join condition. The WHERE clause filters out the rows where the id column in your_table is not null, effectively returning only the missing IDs.

Simply replace your_table with the name of the table you want to check for missing IDs.

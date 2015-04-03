OLMHash based on the following requirements and objectives:

The result of OLMHash should be an updatable table of two columns
  - the user id and the hash of the corresponding user's password stored in a file
  
1. If the user id is not found in the table, the program asks the user to enter a new password. 
The hash of the password is then computed and added to the table alongside the user id.
2. If the user id already exists in the table, the user is prompted for his old password. The hash 
of the entered password is computed and compared to the hash of the user's old password stored in 
the table. If the hashes match, the user is prompted for a new password. The hash of the new password 
then replaces that of the old password.
3. The defend against brute-force type attacks, many password systems limit the number of 'wrong' attempts 
for a given user. The program allows 3 attempts.

-User id's must be at least 4 characters, but less than 32 characters long.

-Passwords must include upper and lower case letters and numbers only.

-Passwords can contain a maximum of 12 characters. 

-If chosen passwords are longer than 12, any extra characters after the 12th are ignored. If chosen passwords 
are less than 12 characters, they will bepadded with null characters to make it exactly 12 characters.

-The hashing algorithm first converts all the lower case letters to their upper case equivalent. This will give 
us 36 possible `true' characters to pick from.

-The 12 characters of the chosen password are then split into three 4-character blocks, B0B1B2. We will use the 
ASCII representation of each character, i.e. each block, Bi contains 32 bits.

-E(), which is the OLM Hash function, which takes 32 bits as input and produces 32 bits as output. To produce 
the hash, we take each block of 4 characters Bi and compute hi = E(Bi). The hash of the password is simply the
concatenation of h1, h2, and h3, i.e. h = h1h2h3.

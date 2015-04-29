<?php
	include 'customer.php';
	include 'employee.php';
	include 'inventory.php';
	include 'pharmacy.php';
	include 'supplier.php';
	include 'log.php';
	
	//PharmacyDatabase: handel all actions preformed on pharmacy database
	class PharmacyDatabase 
	{
		var $customer,$employee,$inventory,$pharmacy,$supplier,$log;
		var $connection;
		
		function __construct($conn) {
			
			$this->connection = $conn;		
			$this->customer = new Customer($this->connection);
			$this->employee = new Employee($this->connection);
			$this->inventory = new Inventory($this->connection);
			$this->pharmacy = new Pharmacy($this->connection);
			$this->supplier = new Supplier($this->connection);	
		}
		
		function class_name()
		{
			echo "PharmacyDatabase<br>";
		}
		
		function tableNames(){
			$this->customer->to_string();
			$this->employee->to_string();
			$this->inventory->to_string();
			$this->pharmacy->to_string();
			$this->supplier->to_string();
			$this->log->to_string();
		}
		
		//sample populate to see if database is working
		function sample_populate()
		{	
			$this->customer->insert('Caroline Baker','9051111111','cbaker@gmail.com','1998/12/04','1234 Crazy Ave','0001');
			$this->customer->insert('Charles Edmunds','4169872031','cedmunds@hotmail.com','1990/10/26','5125 Tech Rd','0023');
			$this->customer->insert('David Anderson','9058763145','danderson@gmail.com','1995/01/08','10 Wonderland St','0004,0005,0009');
			$this->customer->insert('Natalie Greene','9873524584','ngreene@outlook.com','1942/03/12','365 Moon Cres','0001,0003,0005,0023');
			$this->customer->insert('Fiona Bond','6476874854','fbond@rogers.com','1987/05/13','8747 Street Rd','0002,0003,0005,0006,0333');
		
			$this->employee->insert('Eric Reid','1234567890','1990/06/08','123 Best Rd','2003/04/04',25.96,'Manager',0001);
			$this->employee->insert('John Russell','5478548961','1986/11/08','5640 Hoodington Rd','2005/07/03',10.50,'Cashier',0002);
			$this->employee->insert('Anthony Payne','3632145117','1995/12/15','654 Billybob Ave','2009/08/13',15.85,'Sales Associate',0003);
			$this->employee->insert('Amy Mackay','2546978546','2001/01/01','9824 Jimmy St','2011/09/10',20.36,'Supervisor',0002);
			$this->employee->insert('Tracey Fisher','9876543210','1956/02/12','30 Saturn Cres','1999/03/09',10.50,'Cashier',0001);
			$this->employee->insert('Inder Pabla','9876543210','1901/02/12','30 Test Cres','1922/03/09',10.50,'Janitor',0001);
			
			$this->inventory->insert(1.50,5.96,'Advil',1,0,0001);
			$this->inventory->insert(1.76,7.80,'Tylenol',1,1,0002);
			$this->inventory->insert(3.47,4.98,'Motrin',0,0,0003);
			$this->inventory->insert(2.34,6.93,'Pepto-Bismol',0,1,0004);
			$this->inventory->insert(5.64,10.35, 'Buckleys',1,0,0005);
				
			$this->pharmacy->insert('123 Medical Rd','9am-5pm','9876543210');
			$this->pharmacy->insert('321 Pharmacy Dr','9am-6pm','1587946352');
            $this->pharmacy->insert('879 Doctor Ave','10am-1pm','0213525748');
            $this->pharmacy->insert('1536 West Rd','7am-9pm','3602514898');
			$this->pharmacy->insert('6351 East St','11am-7pm','7458961320');
		
			$this->supplier->insert('123 Supplier St','Total Health','123654897');
		    $this->supplier->insert('85 Road St','Lighthouse','9876521420');
		    $this->supplier->insert('96 Fitness Ave','Medical Supplier','3642587954');
		    $this->supplier->insert('110 Health Rd','Health Solutions','1239865741');
		    $this->supplier->insert('12345 Candy Cres','Pfizer','3625147896');
		}
	}
?>
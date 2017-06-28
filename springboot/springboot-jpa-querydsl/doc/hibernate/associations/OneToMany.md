######　单向  @OneToMany association

- 实体:

	@Entity(name = "Person")
	public static class Person {
	
	    @Id
	    @GeneratedValue
	    private Long id;
	    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Phone> phones = new ArrayList<>();
	
	    public Person() {
	    }
	
	    public List<Phone> getPhones() {
	        return phones;
	    }
	}
	
	@Entity(name = "Phone")
	public static class Phone {
	
	    @Id
	    @GeneratedValue
	    private Long id;
	
	    @Column(name = "`number`")
	    private String number;
	
	    public Phone() {
	    }
	
	    public Phone(String number) {
	        this.number = number;
	    }
	
	    public Long getId() {
	        return id;
	    }
	
	    public String getNumber() {
	        return number;
	    }
	}
	
- sql生成：

	CREATE TABLE Person (
	    id BIGINT NOT NULL ,
	    PRIMARY KEY ( id )
	)

	CREATE TABLE Person_Phone (
	    Person_id BIGINT NOT NULL ,
	    phones_id BIGINT NOT NULL
	)
	
	CREATE TABLE Phone (
	    id BIGINT NOT NULL ,
	    number VARCHAR(255) ,
	    PRIMARY KEY ( id )
	)
	
	ALTER TABLE Person_Phone
	ADD CONSTRAINT UK_9uhc5itwc9h5gcng944pcaslf
	UNIQUE (phones_id)
	
	ALTER TABLE Person_Phone
	ADD CONSTRAINT FKr38us2n8g5p9rj0b494sd3391
	FOREIGN KEY (phones_id) REFERENCES Phone
	
	ALTER TABLE Person_Phone
	ADD CONSTRAINT FK2ex4e4p7w1cj310kg2woisjl2
	FOREIGN KEY (Person_id) REFERENCES Person

- 操作代码：

	Person person = new Person();
	Phone phone1 = new Phone( "123-456-7890" );
	Phone phone2 = new Phone( "321-654-0987" );
	
	person.getPhones().add( phone1 );
	person.getPhones().add( phone2 );
	entityManager.persist( person );
	entityManager.flush();
	
	person.getPhones().remove( phone1 );
	
	-----------------------------------------------
	INSERT INTO Person
       ( id )
	VALUES ( 1 )
	
	INSERT INTO Phone
	       ( number, id )
	VALUES ( '123 - 456 - 7890', 2 )
	
	INSERT INTO Phone
	       ( number, id )
	VALUES ( '321 - 654 - 0987', 3 )
	
	INSERT INTO Person_Phone
	       ( Person_id, phones_id )
	VALUES ( 1, 2 )
	
	INSERT INTO Person_Phone
	       ( Person_id, phones_id )
	VALUES ( 1, 3 )
	
	DELETE FROM Person_Phone
	WHERE  Person_id = 1
	
	INSERT INTO Person_Phone
	       ( Person_id, phones_id )
	VALUES ( 1, 3 )
	
	DELETE FROM Phone
	WHERE  id = 2
	
###### 双向 @OneToMany

- 实体：

	@Entity(name = "Person")
	public static class Person {
	
	    @Id
	    @GeneratedValue
	    private Long id;
	    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Phone> phones = new ArrayList<>();
	
	    public Person() {
	    }
	
	    public Person(Long id) {
	        this.id = id;
	    }
	
	    public List<Phone> getPhones() {
	        return phones;
	    }
	
	    public void addPhone(Phone phone) {
	        phones.add( phone );
	        phone.setPerson( this );
	    }
	
	    public void removePhone(Phone phone) {
	        phones.remove( phone );
	        phone.setPerson( null );
	    }
	}		
	
	@Entity(name = "Phone")
	public static class Phone {
	
	    @Id
	    @GeneratedValue
	    private Long id;
	
	    @NaturalId
	    @Column(name = "`number`", unique = true)
	    private String number;
	
	    @ManyToOne
	    private Person person;
	
	    public Phone() {
	    }
	
	    public Phone(String number) {
	        this.number = number;
	    }
	
	    public Long getId() {
	        return id;
	    }
	
	    public String getNumber() {
	        return number;
	    }
	
	    public Person getPerson() {
	        return person;
	    }
	
	    public void setPerson(Person person) {
	        this.person = person;
	    }
	
	    @Override
	    public boolean equals(Object o) {
	        if ( this == o ) {
	            return true;
	        }
	        if ( o == null || getClass() != o.getClass() ) {
	            return false;
	        }
	        Phone phone = (Phone) o;
	        return Objects.equals( number, phone.number );
	    }
	
	    @Override
	    public int hashCode() {
	        return Objects.hash( number );
	    }
	}
	
- 生成sql：

	CREATE TABLE Person (
	    id BIGINT NOT NULL ,
	    PRIMARY KEY ( id )
	)
	
	CREATE TABLE Phone (
	    id BIGINT NOT NULL ,
	    number VARCHAR(255) ,
	    person_id BIGINT ,
	    PRIMARY KEY ( id )
	)
	
	ALTER TABLE Phone
	ADD CONSTRAINT UK_l329ab0g4c1t78onljnxmbnp6
	UNIQUE (number)
	
	ALTER TABLE Phone
	ADD CONSTRAINT FKmw13yfsjypiiq0i1osdkaeqpg
	FOREIGN KEY (person_id) REFERENCES Person

- 操作：

	Person person = new Person();
	Phone phone1 = new Phone( "123-456-7890" );
	Phone phone2 = new Phone( "321-654-0987" );
	
	person.addPhone( phone1 );
	person.addPhone( phone2 );
	entityManager.persist( person );
	entityManager.flush();
	
	person.removePhone( phone1 );	
	
	-----------------------------------------
	
	INSERT INTO Phone
       ( number, person_id, id )
	VALUES ( '123-456-7890', NULL, 2 )
	
	INSERT INTO Phone
	       ( number, person_id, id )
	VALUES ( '321-654-0987', NULL, 3 )
	
	DELETE FROM Phone
	WHERE  id = 2
	

	
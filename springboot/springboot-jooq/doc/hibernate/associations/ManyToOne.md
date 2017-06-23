###### @ManyToOne association
相当于外键

- 实体

	@Entity(name = "Person")
	public static class Person {
	
		@Id
		@GeneratedValue
		private Long id;
		
		public Person() {
		
		}
	}
	
	@Entity(name = "Phone")
	public static class Phone {
	
	    @Id
	    @GeneratedValue
	    private Long id;
	
	    @Column(name = "`number`")
	    private String number;
	
	    @ManyToOne
	    @JoinColumn(name = "person_id",
	            foreignKey = @ForeignKey(name = "PERSON_ID_FK")
	    )
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
	}
	
- sql

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
	ADD CONSTRAINT PERSON_ID_FK
	FOREIGN KEY (person_id) REFERENCES Person
	
- 生命周期

	Person person = new Person();
	entityManager.persist( person );
	
	Phone phone = new Phone( "123-456-7890" );
	phone.setPerson( person );
	entityManager.persist( phone );
	
	entityManager.flush();
	phone.setPerson( null );
	
	实际sql：
	INSERT INTO Person ( id )
	VALUES ( 1 )
	
	INSERT INTO Phone ( number, person_id, id )
	VALUES ( '123-456-7890', 1, 2 )
	
	UPDATE Phone
	SET    number = '123-456-7890',
	       person_id = NULL
	WHERE  id = 2

	
	
	
	
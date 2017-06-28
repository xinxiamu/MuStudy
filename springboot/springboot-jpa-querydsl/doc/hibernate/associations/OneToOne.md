###### 单向 @OneToOne

- 实体：

	@Entity(name = "Phone")
	public static class Phone {
	
	    @Id
	    @GeneratedValue
	    private Long id;
	
	    @Column(name = "`number`")
	    private String number;
	
	    @OneToOne
	    @JoinColumn(name = "details_id")
	    private PhoneDetails details;
	
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
	
	    public PhoneDetails getDetails() {
	        return details;
	    }
	
	    public void setDetails(PhoneDetails details) {
	        this.details = details;
	    }
	}
	
	@Entity(name = "PhoneDetails")
	public static class PhoneDetails {
	
	    @Id
	    @GeneratedValue
	    private Long id;
	
	    private String provider;
	
	    private String technology;
	
	    public PhoneDetails() {
	    }
	
	    public PhoneDetails(String provider, String technology) {
	        this.provider = provider;
	        this.technology = technology;
	    }
	
	    public String getProvider() {
	        return provider;
	    }
	
	    public String getTechnology() {
	        return technology;
	    }
	
	    public void setTechnology(String technology) {
	        this.technology = technology;
	    }
	}
	
- 生成sql：

	CREATE TABLE Phone (
	    id BIGINT NOT NULL ,
	    number VARCHAR(255) ,
	    details_id BIGINT ,
	    PRIMARY KEY ( id )
	)
	
	CREATE TABLE PhoneDetails (
	    id BIGINT NOT NULL ,
	    provider VARCHAR(255) ,
	    technology VARCHAR(255) ,
	    PRIMARY KEY ( id )
	)
	
	ALTER TABLE Phone
	ADD CONSTRAINT FKnoj7cj83ppfqbnvqqa5kolub7
	FOREIGN KEY (details_id) REFERENCES PhoneDetails
	
- 操作：

	作为外键，相当于ManyToOne作为外键操作
	
###### 双向 @OneToOne

- 实体：

	@Entity(name = "Phone")
	public static class Phone {
	
	    @Id
	    @GeneratedValue
	    private Long id;
	
	    @Column(name = "`number`")
	    private String number;
	
	    @OneToOne(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	    private PhoneDetails details;
	
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
	
	    public PhoneDetails getDetails() {
	        return details;
	    }
	
	    public void addDetails(PhoneDetails details) {
	        details.setPhone( this );
	        this.details = details;
	    }
	
	    public void removeDetails() {
	        if ( details != null ) {
	            details.setPhone( null );
	            this.details = null;
	        }
	    }
	}
	
	@Entity(name = "PhoneDetails")
	public static class PhoneDetails {
	
	    @Id
	    @GeneratedValue
	    private Long id;
	
	    private String provider;
	
	    private String technology;
	
	    @OneToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "phone_id")
	    private Phone phone;
	
	    public PhoneDetails() {
	    }
	
	    public PhoneDetails(String provider, String technology) {
	        this.provider = provider;
	        this.technology = technology;
	    }
	
	    public String getProvider() {
	        return provider;
	    }
	
	    public String getTechnology() {
	        return technology;
	    }
	
	    public void setTechnology(String technology) {
	        this.technology = technology;
	    }
	
	    public Phone getPhone() {
	        return phone;
	    }
	
	    public void setPhone(Phone phone) {
	        this.phone = phone;
	    }
	}

- 生成sql：

	CREATE TABLE Phone (
	    id BIGINT NOT NULL ,
	    number VARCHAR(255) ,
	    PRIMARY KEY ( id )
	)
	
	CREATE TABLE PhoneDetails (
	    id BIGINT NOT NULL ,
	    provider VARCHAR(255) ,
	    technology VARCHAR(255) ,
	    phone_id BIGINT ,
	    PRIMARY KEY ( id )
	)
	
	ALTER TABLE PhoneDetails
	ADD CONSTRAINT FKeotuev8ja8v0sdh29dynqj05p
	FOREIGN KEY (phone_id) REFERENCES Phone

- 操作：

	Phone phone = new Phone( "123-456-7890" );
	PhoneDetails details = new PhoneDetails( "T-Mobile", "GSM" );
	
	phone.addDetails( details );
	entityManager.persist( phone );
	
	-------------------------------------
	INSERT INTO Phone ( number, id )
	VALUES ( '123 - 456 - 7890', 1 )
	
	INSERT INTO PhoneDetails ( phone_id, provider, technology, id )
	VALUES ( 1, 'T - Mobile, GSM', 2 )
	
	
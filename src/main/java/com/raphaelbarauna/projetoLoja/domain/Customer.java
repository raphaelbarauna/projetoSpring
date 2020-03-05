package com.raphaelbarauna.projetoLoja.domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.raphaelbarauna.projetoLoja.domain.enums.TypeCustomer;

@Entity
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	@Column(unique=true)
	private String email;
	private String cpfOrCnpj;
	private Integer type;

	@OneToMany
	@OrderColumn(name = "id")
	private Text[] listaText = new Text[10];
	
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
	private List<Adress> adresses = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="MOBILE")
	private Set<String> mobiles = new HashSet<>();
	
	//os pedidos nao vao ser serializados
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Order> orders = new ArrayList<>();


	public Customer() {

	}

	public Customer(Integer id, String name, String email, String cpfOrCnpj, TypeCustomer type) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOrCnpj = cpfOrCnpj;
		this.type = (type==null) ? null : type.getCod();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}
    //armazenar o numero inteiro porém ira exibir o tipoCliente
	public TypeCustomer getType() {
		return TypeCustomer.toEnum(type);
	}

	public void setType(TypeCustomer type) {
		this.type = type.getCod();
	}

	public List<Adress> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adress> adresses) {
		this.adresses = adresses;
	}

	public Set<String> getMobiles() {
		return mobiles;
	}

	public void setMobiles(Set<String> mobiles) {
		this.mobiles = mobiles;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public void adiciona(Text listaText) {
		for (int i = 0; i < this.listaText.length; i++) {
			if (this.listaText[i] == null) {
				this.listaText[i] = listaText;
				break;
			}
		}
	}

	public Text[] getListaText() {
		return listaText;
	}

	public void setListaText(Text[] listaText) {
		this.listaText = listaText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", cpfOrCnpj='" + cpfOrCnpj + '\'' +
				", type=" + type +
				", listaText=" + Arrays.toString(listaText) +
				'}';
	}
}

package com.raphaelbarauna.projetoLoja.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;




@Entity
@Table(name = "order_table")
public class Order implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idOrder;
	
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date instante;
	
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="order")
	private Payment payment;
	
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="delivery_adress_id")
	private Adress deliveryAdress;
	
	//Nao ter itens repetidos
	@OneToMany(mappedBy = "id.order")
	private Set<ItemOrder> items = new HashSet<>();
	
	public Order() {
		
	}

	public Order(Integer ioOrder, Date instante, Customer customer, Adress deliveryAdress) {
		super();
		this.idOrder = ioOrder;
		this.instante = instante;
		this.customer = customer;
		this.deliveryAdress = deliveryAdress;
	}

	public double getValorTotal() {
		
		double soma = 0.0;
		for(ItemOrder ip : items) {
			soma = soma + ip.getSubTotal();
		}
		return soma;
	}
	
	public Integer getId() {
		return idOrder;
	}

	public void setId(Integer idOrder) {
		this.idOrder = idOrder;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Adress getDeliveryAdress() {
		return deliveryAdress;
	}

	public void setDeliveryAdress(Adress deliveryAdress) {
		this.deliveryAdress = deliveryAdress;
	}
	
	public Set<ItemOrder> getItems() {
		return items;
	}

	public void setItems(Set<ItemOrder> items) {
		this.items = items;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idOrder == null) ? 0 : idOrder.hashCode());
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
		Order other = (Order) obj;
		if (idOrder == null) {
			if (other.idOrder != null)
				return false;
		} else if (!idOrder.equals(other.idOrder))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número");
		builder.append(getId());
		builder.append(getInstante());
		builder.append(", Cliente: ");
		builder.append(getCustomer().getName());
		builder.append("Situação do pagamento");
		builder.append(getPayment().getStatus().getDescription());
		builder.append("\nDetalhes:\n");
		for(ItemOrder ip : getItems()) {
			builder.append(ip.toString());
		}
		builder.append("Valor total: ");
		builder.append(getValorTotal());
		return builder.toString();
	}


	
	}
	
	
	


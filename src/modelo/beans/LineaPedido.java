package modelo.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the linea_pedidos database table.
 * 
 */
@Entity
@Table(name = "linea_pedidos")
@NamedQuery(name = "LineaPedido.findAll", query = "SELECT l FROM LineaPedido l")
public class LineaPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LineaPedidoPK id;

	private int cantidad;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_ALTA")
	private Date fechaAlta;

	@Column(name = "PRECIO_VENTA")
	private BigDecimal precioVenta;

	// bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name = "ID_PEDIDO")
	private Pedido pedido;

	// uni-directional many-to-one association to Libro
	@ManyToOne
	@JoinColumn(name = "ISBN")
	private Libro libro;

	public LineaPedido(LineaPedidoPK id, int cantidad, Date fechaAlta, BigDecimal precioVenta, Pedido pedido,
			Libro libro) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.fechaAlta = fechaAlta;
		this.precioVenta = precioVenta;
		this.pedido = pedido;
		this.libro = libro;
	}

	public LineaPedido() {
	}

	public LineaPedidoPK getId() {
		return this.id;
	}

	public void setId(LineaPedidoPK id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public BigDecimal getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Libro getLibro() {
		return this.libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	@Override
	public String toString() {
		return "LineaPedido [id=" + id + ", cantidad=" + cantidad + ", fechaAlta=" + fechaAlta + ", precioVenta="
				+ precioVenta + ", pedido=" + pedido + ", libro=" + libro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + ((fechaAlta == null) ? 0 : fechaAlta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((libro == null) ? 0 : libro.hashCode());
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((precioVenta == null) ? 0 : precioVenta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof LineaPedido))
			return false;
		LineaPedido other = (LineaPedido) obj;
		if (cantidad != other.cantidad)
			return false;
		if (fechaAlta == null) {
			if (other.fechaAlta != null)
				return false;
		} else if (!fechaAlta.equals(other.fechaAlta))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (libro == null) {
			if (other.libro != null)
				return false;
		} else if (!libro.equals(other.libro))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (precioVenta == null) {
			if (other.precioVenta != null)
				return false;
		} else if (!precioVenta.equals(other.precioVenta))
			return false;
		return true;
	}

}
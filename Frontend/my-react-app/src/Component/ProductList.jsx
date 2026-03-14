import axios from "axios";
import { useEffect, useState } from "react";
import iphone from "../assets/download.jpeg";

export default function ProductList() {

    const PRODUCT_URL = "http://localhost:8080/api/products/list";
    const [products, setProducts] = useState([]);


    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await axios.get(PRODUCT_URL, {
                    withCredentials: true
                });

                const data = response.data;
                setProducts(data);

            } catch (error) {
                console.error("Error fetching products:", error);
            }
        }
        fetchProducts();
    }, []);

    return (
        <div className="container mt-5">
            <div className="row">

                {products.map((product) => (
                    <div className="col-md-3 mb-4" key={product.id}>
                        <div className="card">

                            <img src={iphone} className="card-img-top" alt="product" />

                            <div className="card-body">
                                <h5 className="card-title">{product.name}</h5>

                                <p className="card-text">
                                    Price: {product.salePrice}
                                </p>

                                <button className="btn btn-primary">
                                    Buy
                                </button>

                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}
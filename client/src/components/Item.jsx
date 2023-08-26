const Item = ({ name, description }) => {
    return (
        <div className="div--style">
            <h3>{name}</h3>
            <p>{description}</p>
        </div>
    )
}

export default Item

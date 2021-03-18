import Card from '../Components/Card/Card'
import dummyData from '../../../client/src/DummyData';

export const TeamComponent = () => {
return (

    <div className="company-page-container">
        <div className="title">
            <h2>Team Page</h2>
        </div>
        <div className="card-container">
            {dummyData.data[2].teams.map((team) => (
              <Card
                className="card"
                name={team.name}
                key={`${team.name}-${team.id}`}
              />
            ))}
        </div>
    </div> 
)
}
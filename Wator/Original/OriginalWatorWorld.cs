using System;
using System.Drawing;

namespace VSS.Wator.Original
{
    // object-oriented implementation of the wator world simulation
    public class OriginalWatorWorld : IWatorWorld
    {
        // random number generator
        private Random random;

        // A matrix of ints that determines the order of execution of each cell of the world.
        // this matrix is shuffled in each time step.
        // Cells of the world must be executed in a random order,
        // otherwise the animal in the first cell is always allowed to move first.
        private int[] randomMatrix;

        // for visualization
        private byte[] rgbValues;

        #region Properties

        // width (number of cells) of the world
        public int Width { get; private set; }
        // height (number of cells) of the world
        public int Height { get; private set; }
        // the cells of the world (2D-array of animal (fish or shark), empty cells have the value null)
        public Animal[,] Grid { get; private set; }

        // simulation parameters
        public int InitialFishPopulation { get; private set; }
        public int InitialFishEnergy { get; private set; }
        public int FishBreedTime { get; private set; }

        public int InitialSharkPopulation { get; private set; }
        public int InitialSharkEnergy { get; private set; }
        public int SharkBreedEnergy { get; private set; }

        /// <summary>
        /// Gets the current iteration of the world
        /// </summary>
        public long CurrentIteration { get; private set; }

        #endregion

        // create and init a new wator world with the given settings
        public OriginalWatorWorld(Settings settings)
        {
            // copy settings 
            Width = settings.Width;
            Height = settings.Height;
            InitialFishPopulation = settings.InitialFishPopulation;
            InitialFishEnergy = settings.InitialFishEnergy;
            FishBreedTime = settings.FishBreedTime;
            InitialSharkPopulation = settings.InitialSharkPopulation;
            InitialSharkEnergy = settings.InitialSharkEnergy;
            SharkBreedEnergy = settings.SharkBreedEnergy;

            rgbValues = new byte[Width*Height*4];

            random = new Random();
            Grid = new Animal[Width, Height];

            // initialize the population by placing the required number of shark and fish
            // randomly on the grid
            // randomMatrix contains all values from 0 .. Width*Height in a random ordering
            // so we can simply place a fish onto a cell if the value in the same cell of the
            // randomMatrix is smaller then the number of fish 
            // subsequently we can place a shark if the number in randomMatrix is smaller than
            // the number of fish and shark
            for (int i = 0; i < Width; i++)
            {
                for (int j = 0; j < Height; j++)
                {
                    int value = random.Next(Width*Height);
                    if (value < InitialFishPopulation)
                    {
                        Grid[i, j] = new Fish(this, new Point(i, j), random.Next(0, FishBreedTime));
                    }
                    else if (value < InitialFishPopulation + InitialSharkPopulation)
                    {
                        Grid[i, j] = new Shark(this, new Point(i, j), random.Next(0, SharkBreedEnergy));
                    }
                    else
                    {
                        Grid[i, j] = null;
                    }
                }
            }

            // populate the random matrix that determines the order of execution for the cells
            randomMatrix = GenerateRandomMatrix(Width, Height);
        }

        // execute one time step of the simulation. Each cell of the world must be executed once
        // Animal move around on the grid. To make sure each animal is executed only once we
        // use the moved flag.
        public void ExecuteStep()
        {
            CurrentIteration++;
            // shuffle the values in randomMatrix to make sure
            // that in each time step the order of execution of cells is different (and random)
            RandomizeMatrix(randomMatrix);

            // go over all cells of the random matrix
            int row, col;
            for (int i = 0; i < Width*Height; i++)
            {
                // determine row and col of the grid cell by manipulating the value
                var value = randomMatrix[i];
                col = value % Width;
                row = value / Width;

                // if there is an animal on this cell that has not been moved in this simulation step
                // then we execute it
                if (Grid[col, row] != null && !Grid[col, row].Moved)
                    Grid[col, row].ExecuteStep();
                
            }

          
        }

        // generates a bitmap for the current state of the wator world
        public Bitmap GenerateImage()
        {
            int counter = 0;
            for (int y = 0; y < Height; y++)
                for (int x = 0; x < Width; x++)
                {
                    Color col;
                    if (Grid[x, y] == null) col = Color.DarkBlue;
                    else col = Grid[x, y].Color;

                    rgbValues[counter++] = col.B; //  // b
                    rgbValues[counter++] = col.G; // // g
                    rgbValues[counter++] = col.R; //  // R
                    rgbValues[counter++] = col.A; //  // a
                }
            // Lock the bitmap's bits.  
            Rectangle rect = new Rectangle(0, 0, Width, Height);
            var bitmap = new Bitmap(Width, Height);
            System.Drawing.Imaging.BitmapData bmpData = null;
            try
            {
                bmpData = bitmap.LockBits(rect, System.Drawing.Imaging.ImageLockMode.ReadWrite, bitmap.PixelFormat);

                // Get the address of the first line.
                IntPtr ptr = bmpData.Scan0;

                // Copy the RGB values back to the bitmap
                System.Runtime.InteropServices.Marshal.Copy(rgbValues, 0, ptr, rgbValues.Length);
            }
            finally
            {
                // Unlock the bits.
                if (bmpData != null) bitmap.UnlockBits(bmpData);
            }
            return bitmap;
        }

        /// <summary>
        /// Neighbors static data array which is reused
        /// </summary>
        private readonly Point[] neighbors = new Point[4];

        // find all neighbouring cells of the given position that contain an animal of the given type
        public Point SelectNeighbor(Type type, Point position)
        {
            int neighborIndex;
            int i, j;

            // counter for the number of cells of the correct type
            neighborIndex = 0;
            // look up
            i = position.X;
            j = (position.Y + Height - 1) % Height;
            if (CheckNeighbor(type, i,j ))
            {
                neighbors[neighborIndex].X = i;
                neighbors[neighborIndex].Y = j;
                neighborIndex++;
            }

            i = (position.X + 1) % Width;
            j = position.Y;
            if (CheckNeighbor(type,i, j))
            {
                neighbors[neighborIndex].X = i;
                neighbors[neighborIndex].Y = j;
                neighborIndex++;
            }

            // look down
            i = position.X;
            j = (position.Y + 1) % Height;
            if (CheckNeighbor(type, i, j))
            {
                neighbors[neighborIndex].X = i;
                neighbors[neighborIndex].Y = j;
                neighborIndex++;
            }

            // look left
            i = (position.X + Width - 1) % Width;
            j = position.Y;
            if (CheckNeighbor(type, i, j))
            {
                neighbors[neighborIndex].X = i;
                neighbors[neighborIndex].Y = j;
                neighborIndex++;
            }

            if (neighborIndex > 1)
            {
                // if more than one cell has been found => return a randomly selected cell
                return neighbors[random.Next(neighborIndex)];
            }
            else if (neighborIndex == 1)
            {
                // if only a single cell contains an animal of the given type we can save the call to random
                return neighbors[0];
            }
            else
            {
                // return a point with negative coordinates to indicate
                // that no neighbouring cell has found
                // return value must be checked by the caller
                return new Point(-1, -1);
            }
        }
        
        private bool CheckNeighbor(Type type, int xCoord, int yCoord)
        {
            var value = Grid[xCoord, yCoord];
            if ((type == null) && (value  == null))
            {
                return true;
            }
            else if ((type != null) && (type.IsInstanceOfType(value)))
            {
                if ((value != null) && (!value.Moved))
                {
                    return true;
                }
            }
            return false;
        }

        

        // create a 2D array containing all numbers in the range 0 .. width * height
        // the numbers are shuffled to create a random ordering
        private int[] GenerateRandomMatrix(int width, int height)
        { 
            int[] matrix = new int[width * height];

            for (int i = 0; i < matrix.Length; i++)
            {
               matrix[i]=i;
            }
            // shuffle matrix
            RandomizeMatrix(matrix);
            return matrix;
        }

        // shuffle the values of the 2D array in a random fashion
        private void RandomizeMatrix(int[] matrix)
        {
            //Knuth shuffle for arrays 
            //https://www.rosettacode.org/wiki/Knuth_shuffle#C.23
            for (int i = 0; i < matrix.Length; i++)
            {
                int j = random.Next(i, matrix.Length); // Don't select from the entire array on subsequent loops
                int temp = matrix[i];
                matrix[i] = matrix[j];
                matrix[j] = temp;
            }
        }
    }
}